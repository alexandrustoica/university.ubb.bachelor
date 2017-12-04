package store.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.stereotype.Component;
import store.deposit.Deposit;
import store.deposit.ProductDeposit;
import store.employee.Cashier;
import store.employee.DepositWorker;
import store.employee.Employee;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;


@Immutable
@Component
public class Store {

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(10);
    private final Deposit<Product> deposit = new ProductDeposit();
    private final Employee cashier = new Cashier();

    private final Function<Employee, Employee> withDepositWorker =
            (employee) -> new DepositWorker(employee, deposit);

    public Future<Product> deposit(final Product product, final Integer quantity) {
        return executorService.submit(() -> deposit.deposit(product, quantity));
    }

    public Future<Invoice> sell(final Integer productId, final Integer quantity) {
        return executorService.submit(() -> withDepositWorker.apply(cashier)
                .sell(deposit.findById(productId), quantity));
    }

    public Double total() {
        return cashier.totalProfit();
    }

    public List<Product> products() {
        return deposit.all();
    }
}

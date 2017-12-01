package store.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import store.cashier.Cashier;
import store.cashier.CheckerCashier;
import store.cashier.CheckoutCashier;
import store.cashier.DepositCashier;
import store.deposit.Deposit;
import store.deposit.ProductDeposit;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;


@Immutable
public class Store {

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(10);
    private final Deposit<Product> deposit = new ProductDeposit();
    private final Cashier cashier = new CheckoutCashier();

    private final Function<Cashier, Cashier> employee = (cashier) ->
            new CheckerCashier(new DepositCashier(cashier, deposit), deposit);

    public Future<Invoice> sell(final Integer productId, final Integer quantity) {
        return executorService.submit(() -> employee.apply(cashier)
                .sell(deposit.findById(productId), quantity));
    }

    public List<Product> products() {
        return deposit.all();
    }


    public Future<Product> deposit(final Product product, final Integer quantity) {
        return executorService.submit(() -> deposit.deposit(product, quantity));
    }

}

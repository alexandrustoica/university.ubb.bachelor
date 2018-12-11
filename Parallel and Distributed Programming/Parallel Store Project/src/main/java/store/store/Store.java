package store.store;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.deposit.Deposit;
import store.deposit.StockDeposit;
import store.domain.Id;
import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;
import store.employee.Cashier;
import store.employee.Employee;
import store.exception.ProductNotFoundException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;


@Immutable
@Component
public class Store {

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(10);

    private final Deposit<Stock, Integer> deposit;
    private final Employee cashier;

    private Id id = new Id(1);

    public Store() {
        this(new StockDeposit(), new Cashier());
    }

    @Autowired
    public Store(@Qualifier("databaseDeposit") final Deposit<Stock, Integer> deposit,
                 @Qualifier("databaseEmployee") final Employee cashier) {
        this.deposit = deposit;
        this.cashier = cashier;
    }

    public Future<Stock> deposit(final Product product, final Integer quantity) {
        return executorService.submit(() ->
                deposit.deposit(new Stock(product.id(id.generate()), quantity)));
    }

    public Future<Invoice> sell(final Integer productId, final Integer quantity) {
        return executorService.submit(() -> {
                Invoice invoice = cashier.sell(deposit.findById(productId).orElseThrow(ProductNotFoundException::new).quantity(quantity));
                deposit.remove(new Stock(invoice.sell().product(), invoice.sell().quantity()));
                return invoice;
        });
    }

    public Double total() {
        return cashier.totalProfit();
    }

    public List<Product> products() {
        return deposit.elements().stream()
                .map(Stock::product).collect(Collectors.toList());
    }
}

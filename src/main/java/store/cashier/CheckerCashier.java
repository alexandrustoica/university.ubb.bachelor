package store.cashier;

import jdk.nashorn.internal.ir.annotations.Immutable;
import store.deposit.Deposit;
import store.domain.Invoice;
import store.domain.Product;
import store.exception.OverflowSupplyException;

@Immutable
public class CheckerCashier implements Cashier {

    private final Cashier cashier;
    private final Deposit<Product> deposit;

    public CheckerCashier(final Cashier cashier, final Deposit<Product> deposit) {
        this.cashier = cashier;
        this.deposit = deposit;
    }

    @Override
    public Invoice sell(Product product, Integer quantity) {
        if(!deposit.isAvailable(product, quantity))
            throw new OverflowSupplyException();
        return cashier.sell(product, quantity);
    }
}

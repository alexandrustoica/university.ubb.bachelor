package store.cashier;

import store.deposit.Deposit;
import store.domain.Invoice;
import store.domain.Product;

public class DepositCashier implements Cashier {

    private final Cashier cashier;
    private final Deposit<Product> deposit;

    public DepositCashier(final Cashier cashier, final Deposit<Product> deposit) {
        this.cashier = cashier;
        this.deposit = deposit;
    }

    @Override
    public Invoice sell(Product product, Integer quantity) {
        Invoice invoice = cashier.sell(product, quantity);
        deposit.remove(product, quantity);
        return invoice;
    }
}

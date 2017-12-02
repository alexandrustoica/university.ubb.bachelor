package store.employee;

import store.deposit.Deposit;
import store.domain.Invoice;
import store.domain.Product;
import store.exception.OverflowSupplyException;

public class DepositWorker implements Employee {

    private final Employee employee;
    private final Deposit<Product> deposit;

    public DepositWorker(final Employee employee, final Deposit<Product> deposit) {
        this.employee = employee;
        this.deposit = deposit;
    }

    @Override
    public Invoice sell(Product product, Integer quantity) {
        if(!deposit.isAvailable(product, quantity))
            throw new OverflowSupplyException();
        Invoice invoice = employee.sell(product, quantity);
        deposit.remove(product, quantity);
        return invoice;
    }

    @Override
    public Double totalProfit() {
        return employee.totalProfit();
    }
}

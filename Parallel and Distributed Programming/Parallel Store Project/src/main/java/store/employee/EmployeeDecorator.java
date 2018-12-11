package store.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.domain.Invoice;
import store.domain.Stock;

@Component
public class EmployeeDecorator implements Employee {

    protected final Employee employee;

    @Autowired
    public EmployeeDecorator(@Qualifier("cashier") final Employee employee) {
        this.employee = employee;
    }

    @Override
    public Invoice sell(Stock stock) {
        return employee.sell(stock);
    }

    @Override
    public Double totalProfit() {
        return employee.totalProfit();
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return employee.addInvoice(invoice);
    }
}

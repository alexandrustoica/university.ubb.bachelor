package store.employee;

import store.domain.Invoice;
import store.domain.Stock;

public interface Employee extends Accountant {
    Invoice sell(final Stock stock);
    Invoice addInvoice(final Invoice invoice);
}

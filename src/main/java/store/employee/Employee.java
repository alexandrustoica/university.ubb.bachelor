package store.employee;

import store.domain.Invoice;
import store.domain.Product;

public interface Employee extends Accountant {
    Invoice sell(final Product product, final Integer quantity);
}

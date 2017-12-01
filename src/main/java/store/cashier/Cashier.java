package store.cashier;

import store.domain.Invoice;
import store.domain.Product;

public interface Cashier {
    Invoice sell(final Product product, final Integer quantity);
}

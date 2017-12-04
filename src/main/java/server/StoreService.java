package server;

import store.domain.Invoice;
import store.domain.Product;
import store.domain.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface StoreService {
    Product deposit(final Product product, final Integer quantity);
    Invoice sell(final Integer idProduct, final Integer quantity);
    Store store();
}

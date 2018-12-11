package server;

import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;
import store.store.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface StoreService {
    Stock deposit(final Product product, final Integer quantity);
    Invoice sell(final Integer idProduct, final Integer quantity);
    Store store();
    Double total();
}

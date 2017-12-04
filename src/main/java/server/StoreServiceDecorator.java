package server;

import store.domain.Invoice;
import store.domain.Product;
import store.domain.Store;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class StoreServiceDecorator implements StoreService {

    protected StoreService service;

    protected StoreServiceDecorator(final StoreService service) {
        this.service = service;
    }

    @Override
    public Product deposit(Product product, Integer quantity) {
        return service.deposit(product, quantity);
    }

    @Override
    public Invoice sell(Integer idProduct, Integer quantity) {
        return service.sell(idProduct, quantity);
    }

    @Override
    public Store store() {
        return service.store();
    }
}

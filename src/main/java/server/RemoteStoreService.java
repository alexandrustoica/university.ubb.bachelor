package server;

import store.domain.Invoice;
import store.domain.Product;
import store.domain.Store;

import static org.jooq.lambda.Unchecked.supplier;

public class RemoteStoreService implements StoreService {

    private Store store;

    public RemoteStoreService(final Store store) {
        super();
        this.store = store;
    }

    @Override
    public Product deposit(Product product, Integer quantity) {
        return supplier(() -> store.deposit(product, quantity).get()).get();
    }

    @Override
    public Invoice sell(Integer idProduct, Integer quantity) {
        return supplier(() -> store.sell(idProduct, quantity).get()).get();
    }

    @Override
    public Store store() {
        return store;
    }
}

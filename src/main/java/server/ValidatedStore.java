package server;

import store.domain.Invoice;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ValidatedStore extends StoreServiceDecorator {

    private Double totalSoldInCurrentCheck = 0.0;
    private Double totalSoldBeforeCurrentCheck = 0.0;

    public ValidatedStore(final StoreService service) {
        super(service);
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::check, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public Invoice sell(Integer idProduct, Integer quantity) {
        Invoice invoice = super.sell(idProduct, quantity);
        totalSoldInCurrentCheck += invoice.total();
        return invoice;
    }

    private void check() throws RuntimeException {
        if(store().total() == totalSoldInCurrentCheck + totalSoldBeforeCurrentCheck)
            throw new RuntimeException("Store not up to date");
        totalSoldBeforeCurrentCheck += totalSoldInCurrentCheck;
    }
}

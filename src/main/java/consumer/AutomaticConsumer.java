package consumer;

import org.apache.log4j.Logger;
import server.StoreService;
import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public final class AutomaticConsumer implements Runnable {

    private static Logger logger = Logger.getLogger(AutomaticConsumer.class);

    private final ScheduledExecutorService service =
            Executors.newScheduledThreadPool(1);

    private final StoreService store;

    private Runnable task = () -> logger.info(buy(deposit().product()));

    AutomaticConsumer(final StoreService store) {
        this.store = store;
    }

    public Invoice buy(final Product product) {
        return store.sell(product.id(), 1);
    }

    public Stock deposit() {
        return store.deposit(new Product(), 1);
    }

    @Override
    public void run() {
        service.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}

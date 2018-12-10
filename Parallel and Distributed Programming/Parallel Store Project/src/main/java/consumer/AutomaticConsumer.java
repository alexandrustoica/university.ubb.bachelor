package consumer;

import server.StoreService;
import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.jooq.lambda.Unchecked.supplier;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */


public final class AutomaticConsumer implements Runnable {

    private static Logger logger = Logger.getLogger("AutoLogger");

    private final ScheduledExecutorService service =
            Executors.newScheduledThreadPool(1);

    private final StoreService store;

    private Runnable task = () ->
            logger.log(Level.INFO, buy(deposit().product()).toString());

    AutomaticConsumer(final StoreService store) {
        FileHandler fileHandler = supplier(() ->
                new FileHandler("auto_log.log")).get();
        fileHandler.setFormatter(new SimpleFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
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

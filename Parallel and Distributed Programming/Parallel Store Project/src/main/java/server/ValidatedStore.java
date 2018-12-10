package server;

import store.domain.Invoice;
import store.domain.TotalSold;

import java.util.concurrent.Executors;
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

public class ValidatedStore extends StoreServiceDecorator {

    private TotalSold totalSoldInCurrentCheck = new TotalSold(0.0);
    private TotalSold totalSoldBeforeCurrentCheck = new TotalSold(0.0);

    private Logger logger = Logger.getLogger("ValidateLogger");

    public ValidatedStore(final StoreService service) {
        super(service);
        FileHandler fileHandler = supplier(() ->
                new FileHandler("valid.log")).get();
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        Executors.newScheduledThreadPool(1)
                .scheduleAtFixedRate(this::check, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public Invoice sell(Integer idProduct, Integer quantity) {
        Invoice invoice = super.sell(idProduct, quantity);
        totalSoldBeforeCurrentCheck.increment(invoice.total());
        return invoice;
    }

    private void check() throws RuntimeException {
        if(service.total() == totalSoldInCurrentCheck.value() + totalSoldBeforeCurrentCheck.value()) {
            logger.log(Level.INFO, "OK");
        }
        totalSoldBeforeCurrentCheck.increment(totalSoldBeforeCurrentCheck.value());
    }
}

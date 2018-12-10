package consumer;

import org.apache.log4j.Logger;
import org.jooq.lambda.Unchecked;
import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;
import store.store.Store;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.jooq.lambda.Unchecked.supplier;

public class ScheduledStoreConsumer {

    private static Logger logger = Logger.getLogger(ScheduledStoreConsumer.class);

    private static BiFunction<Integer, Store, Future<Invoice>> task = (id, store) -> {
                logger.info("I'm buying product with ID: " + id);
                return store.sell(id, 1);
            };

    public static void main(String[] args) {
        Store store = new Store();
        List<Stock> products = IntStream.range(0, 1000)
                .boxed().parallel()
                .map(Unchecked.function(it -> store.deposit(new Product("Name", 10.0, "kg"), it).get()))
                .collect(Collectors.toList());
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(Unchecked.runnable(() ->
                logger.info(task.apply(new Random().nextInt(100), store).get())),
                0, 5, TimeUnit.SECONDS);
    }
}

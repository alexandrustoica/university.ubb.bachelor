package consumer;

import com.google.common.collect.ImmutableMap;
import server.StoreService;
import store.domain.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.jooq.lambda.Unchecked.supplier;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConsoleConsumer implements Runnable {

    private StoreService store;

    private ImmutableMap<String, String> menu = new ImmutableMap.Builder<String, String>()
            .put("1", "[1] Deposit Product To The Store")
            .put("2", "[2] Buy Product From The Store")
            .put("x", "[x] Exit")
            .build();

    private ImmutableMap<String, Runnable> options = new ImmutableMap.Builder<String, Runnable>()
            .put("1", this::depositProduct)
            .put("2", this::buyProduct)
            .put("x", () -> System.exit(0))
            .build();

    public ConsoleConsumer(final StoreService store) {
        this.store = store;
    }

    public void run() {
        while (true) {
            menu.forEach((key, value) -> System.out.println(value));
            String option = supplier(() -> read("Please select an option ... ")).get();
            options.get(option).run();
        }
    }

    private void depositProduct() {
        Product product = getProductFromUser();
        Integer quantity = getQuantity();
        new Thread(() -> System.out.println(store.deposit(product, quantity))).run();
    }

    private void buyProduct() {
        Integer id = getProductId();
        Integer quantity = getQuantity();
        new Thread(() -> System.out.println(store.sell(id, quantity))).run();
    }

    private String read(final String message) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(message);
        return buffer.readLine();
    }

    private Product getProductFromUser() {
        String productName = supplier(() -> read("Product Name ... ")).get();
        String productUnit = supplier(() -> read("Product Unit ... ")).get();
        Double productPrice = Double.parseDouble(supplier(() -> read("Product Price ... ")).get());
        return new Product(productName, productPrice, productUnit);
    }

    private Integer getProductId() {
        return Integer.parseInt(supplier(() -> read("Product ID ... ")).get());
    }

    private Integer getQuantity() {
        return Integer.parseInt(supplier(() -> read("Quantity ... ")).get());
    }
}

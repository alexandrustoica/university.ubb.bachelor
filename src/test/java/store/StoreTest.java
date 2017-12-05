package store;

import org.jooq.lambda.Unchecked;
import org.junit.Test;
import store.domain.Invoice;
import store.domain.Product;
import store.domain.Stock;
import store.exception.UnavailableStockException;
import store.store.Store;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StoreTest {

    @Test
    public void isStoringProduct_WithSingleProvider() throws Exception {
        // declarations:
        Product product = new Product("Test", 0.0, "kg");
        // when:
        Stock actual = new Store().deposit(product, 1).get();
        // then:
        assertThat(actual.product(), equalTo(product.id(1)));
    }

    @Test
    public void isStoringProduct_WithMultipleProviders() throws Exception {
        // declarations:
        Store store = new Store();
        // when:
        List<Stock> stocks = IntStream.range(0, 100).boxed().parallel()
                .map(Unchecked.function(it -> store.deposit(new Product(), it).get()))
                .collect(Collectors.toList());
        // then:
        assertThat(store.products(), hasSize(100));
        assertThat(store.products().stream()
                        .map(it->it.id()).collect(Collectors.toList()),
                everyItem(is(both(lessThanOrEqualTo(100)).and(greaterThan(0)))));
    }

    @Test
    public void isSellingProduct_WithSingleConsumer() throws Exception {
        // declarations:
        final Integer PRODUCT_ID = 1;
        final Integer QUANTITY = 1;
        Product expected = new Product("Test", 10.0, "kg");
        Store store = new Store();
        // preconditions:
        store.deposit(expected, QUANTITY).get();
        // when:
        Invoice actual = store.sell(PRODUCT_ID, QUANTITY).get();
        // then:
        assertAll(() -> assertThat(store.products(), hasSize(0)),
                () -> assertThat(actual.sell().product(), equalTo(expected.id(1))),
                () -> assertThat(actual.sell().quantity(), equalTo(1)),
                () -> assertThat(actual.total(), equalTo(expected.price())));
    }

    @Test
    public void isSellingProduct_WithMultipleConsumers() throws Exception {
        // declarations:
        Store store = new Store();
        // preconditions:
        IntStream.range(1, 100).boxed().parallel()
                .map(Unchecked.function(it -> store.deposit(new Product("default", 10.0, "kg"), 1).get()))
                .collect(Collectors.toList());
        // when:
        List<Future<Invoice>> futureInvoices = IntStream.range(1, 100).boxed().parallel()
                .map(Unchecked.function(number -> store.sell(number, 1)))
                .collect(Collectors.toList());
        // I'm not waiting for the result.
        List<Invoice> invoices = futureInvoices.stream()
                .map(Unchecked.function(Future::get))
                .collect(Collectors.toList());
        // then:
        assertAll(
                () -> assertThat(store.total(), equalTo(990.0)),
                () -> assertThat(invoices, hasSize(99)),
                () -> assertThat(store.products(), hasSize(0)));
    }

    @Test
    public void isSellingProduct_WhenDemandGraterThanSupply() throws Exception {
        // declarations:
        final Integer PRODUCT_ID = 1;
        final Integer QUANTITY = 1;
        final Integer WANTED_QUANTITY = 2;
        Product product = new Product();
        Store store = new Store();
        // preconditions:
        store.deposit(product, QUANTITY).get();
        // when:
        Future<Invoice> invoice = store.sell(PRODUCT_ID, WANTED_QUANTITY);
        // then:
        Throwable exception = assertThrows(ExecutionException.class, invoice::get);
        assertThat(exception.getCause(), is(new UnavailableStockException()));
    }
}
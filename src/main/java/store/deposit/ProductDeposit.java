package store.deposit;

import jdk.nashorn.internal.ir.annotations.Immutable;
import store.domain.Id;
import store.domain.Product;
import store.domain.Stock;
import store.exception.ProductNotFoundException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static java.util.Collections.synchronizedList;


@Immutable
public class ProductDeposit implements Deposit<Product> {

    private final List<Stock> stocks = synchronizedList(new CopyOnWriteArrayList<>());
    private final Id id = new Id(1);

    @Override
    public Product deposit(final Product product, final Integer quantity) {
        Stock stock = new Stock(generateIdFor(product), quantity);
        stocks.add(stock);
        return stock.product();
    }

    @Override
    public void remove(Product element, Integer quantity) {
        Stock stock = findStockForProduct(element);
        stocks.replaceAll(item -> !item.equals(stock) ?
                stock : stock.reduceQuantity(quantity));
        stocks.removeIf(it -> it.quantity().equals(0));
    }

    @Override
    public Boolean isAvailable(Product element, Integer quantity) {
        return findStockForProduct(element).quantity() < quantity ?
                Boolean.FALSE : Boolean.TRUE;
    }

    @Override
    public Product findById(Integer id) {
        return stocks.stream()
                .filter(it -> it.product().id().equals(id))
                .findAny().map(Stock::product)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public List<Product> all() {
        return stocks.stream()
                .map(Stock::product)
                .collect(Collectors.toCollection(() ->
                        synchronizedList(new CopyOnWriteArrayList<>())));
    }

    private Product generateIdFor(final Product product) {
        return product.id(id.generate());
    }

    private Stock findStockForProduct(final Product product) {
        return stocks.stream()
                .filter(stock -> stock.product().equals(product))
                .findFirst().orElseThrow(ProductNotFoundException::new);
    }
}

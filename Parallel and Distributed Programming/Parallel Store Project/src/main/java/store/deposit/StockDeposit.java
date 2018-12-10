package store.deposit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.domain.Stock;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Collections.synchronizedList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Component
@Qualifier("stockDeposit")
public class StockDeposit implements Deposit<Stock, Integer> {

    private final List<Stock> stocks = synchronizedList(new CopyOnWriteArrayList<>());

    @Override
    public Stock deposit(Stock element) {
        stocks.add(element);
        return element;
    }

    @Override
    public Optional<Stock> remove(Stock element) {
        stocks.replaceAll(item -> !item.product().equals(element.product()) ?
                item : item.reduceQuantity(element.quantity()));
        stocks.removeIf(it -> it.quantity().equals(0));
        return findById(element.product().id());
    }

    @Override
    public Optional<Stock> findById(Integer id) {
        return stocks.stream().filter(it -> it.product().id().equals(id))
                .findFirst();
    }

    @Override
    public List<Stock> elements() {
        return stocks;
    }
}

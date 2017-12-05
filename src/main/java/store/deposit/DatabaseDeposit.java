package store.deposit;


import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.domain.Stock;
import store.repository.StockRepository;

import java.util.Optional;

@Immutable
@Component
@Qualifier("databaseDeposit")
public final class DatabaseDeposit extends DepositDecorator<Stock, Integer> {

    private final StockRepository stockRepository;

    @Autowired
    public DatabaseDeposit(final Deposit<Stock, Integer> deposit,
                           final StockRepository stockRepository) {
        super(deposit);
        stockRepository.findAll().forEach(deposit::deposit);
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock deposit(Stock element) {
        return super.deposit(stockRepository.save(element));
    }

    @Override
    public Optional<Stock> remove(Stock element) {
        Optional<Stock> stock = super.remove(element);
        stock.ifPresent(stockRepository::save);
        if(!stock.isPresent())
            stockRepository.delete(element);
        return super.remove(element);
    }
}

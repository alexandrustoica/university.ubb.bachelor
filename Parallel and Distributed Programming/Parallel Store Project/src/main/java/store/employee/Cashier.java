package store.employee;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import store.domain.*;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Collections.synchronizedList;

@Component
@Qualifier("cashier")
public class Cashier implements Employee {

    private final List<Invoice> invoices =
            synchronizedList(new CopyOnWriteArrayList<>());
    private final List<Sell> sells =
            synchronizedList(new CopyOnWriteArrayList<>());
    private final TotalSold total = new TotalSold(0.0);

    @Override
    public Invoice sell(Stock stock) {
        return makeInvoice(stock);
    }

    private Invoice makeInvoice(final Stock stock) {
        Invoice invoice = new Invoice("Invoice",
                makeSell(stock), stock.product().price() * stock.quantity());
        total.increment(stock.product().price() * stock.quantity());
        invoices.add(invoice);
        return invoice;
    }

    private Sell makeSell(final Stock stock) {
        Sell sell = new Sell(Calendar.getInstance(), stock.product(), stock.quantity());
        sells.add(sell);
        return sell;
    }

    @Override
    public Double totalProfit() {
        return total.value();
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        invoices.add(invoice);
        sells.add(invoice.sell());
        total.increment(invoice.total());
        return invoice;
    }
}

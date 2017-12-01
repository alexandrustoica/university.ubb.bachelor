package store.cashier;

import store.domain.Invoice;
import store.domain.Product;
import store.domain.Sell;
import store.domain.TotalSold;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Collections.synchronizedList;

public class CheckoutCashier implements Cashier {

    private final List<Invoice> invoices = synchronizedList(new CopyOnWriteArrayList<>());
    private final List<Sell> sells = synchronizedList(new CopyOnWriteArrayList<>());
    private TotalSold total = new TotalSold(0.0);

    public Invoice sell(final Product product, final Integer quantity) {
        return makeInvoice(product, quantity);
    }

    private Invoice makeInvoice(final Product product, final Integer quantity) {
        Invoice invoice = new Invoice("Invoice", makeSell(product, quantity), product.price() * quantity);
        synchronized (this) {
            total = total.add(product.price() * quantity);
        }
        invoices.add(invoice);
        return invoice;
    }

    private Sell makeSell(final Product product, final Integer quantity) {
        Sell sell = new Sell(Calendar.getInstance(), product, quantity);
        sells.add(sell);
        return sell;
    }
}

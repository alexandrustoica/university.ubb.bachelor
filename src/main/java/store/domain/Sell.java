package store.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Calendar;

@ToString(exclude = {"date"})
@EqualsAndHashCode(exclude = {"date"})
public class Sell implements Serializable {

    private final Calendar date;
    private Product product;
    private Integer quantity;

    public Sell(final Calendar date,
                final Product product,
                final Integer quantity) {
        this.date = date;
        this.product = product;
        this.quantity = quantity;
    }

    @SuppressWarnings("unused")
    public Calendar date() {
        return date;
    }

    public Product product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }
}

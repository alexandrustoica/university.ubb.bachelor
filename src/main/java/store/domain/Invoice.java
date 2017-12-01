package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;

@Immutable
@EqualsAndHashCode()
public class Invoice {
    private String name;
    private Sell sell;
    private Double total;

    public Invoice(final String name, final Sell sell, final Double total) {
        this.name = name;
        this.sell = sell;
        this.total = total;
    }

    @SuppressWarnings("unused")
    public String name() {
        return name;
    }

    public Sell sell() {
        return sell;
    }

    public Double total() {
        return total;
    }
}

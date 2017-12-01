package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;

@Immutable
@EqualsAndHashCode()
public class TotalSold {

    private Double value;

    public TotalSold(final Double value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    public Double value() {
        return value;
    }

    public TotalSold add(final Double value) {
        return new TotalSold(this.value + value);
    }
}

package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.longBitsToDouble;

@Immutable
@EqualsAndHashCode()
public class TotalSold implements Serializable {

    private final AtomicLong value;

    public TotalSold(final Double value) {
        this.value = new AtomicLong(doubleToLongBits(value));
    }

    public Double value() {
        return longBitsToDouble(value.get());
    }

    @SuppressWarnings("all")
    public TotalSold increment(final Double value) {
        this.value.getAndUpdate(it -> doubleToLongBits(longBitsToDouble(it) + value));
        return this;
    }
}

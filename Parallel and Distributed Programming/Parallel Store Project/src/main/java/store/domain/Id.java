package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Immutable
@ToString()
@SuppressWarnings("unused")
@EqualsAndHashCode(of={"value"})
public class Id implements Serializable {

    private AtomicInteger value;

    public Id(final Integer initial) {
        this.value = new AtomicInteger(initial);
    }

    public Integer value() {
        return value.get();
    }

    public Integer generate() {
        return value.getAndIncrement();
    }

}

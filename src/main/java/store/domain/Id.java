package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
@Immutable
@EqualsAndHashCode(of={"value"})
@ToString()
public class Id {

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

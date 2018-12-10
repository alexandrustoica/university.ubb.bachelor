package model;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ConfigurationModelOneToMany<One, Many> implements ConfigurationModel<One, Many>{

    private final Function<Many, One> supplier;
    private final BiConsumer<Many, One> consumer;

    public ConfigurationModelOneToMany(final Function<Many, One> supplier, final BiConsumer<Many, One> consumer) {
        this.supplier = supplier;
        this.consumer = consumer;
    }

    @Override
    public void consume(One element, Many item) {
        consumer.accept(item, element);
    }

    @Override
    public One supply(Many item) {
        return supplier.apply(item);
    }
}

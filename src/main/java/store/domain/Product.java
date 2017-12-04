package store.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


@SuppressWarnings("ALL")
@Immutable
@EqualsAndHashCode(of={"id"})
@ToString
public class Product implements Serializable {

    private final Integer id;
    private final String name;
    private final Double price;
    private final String unit;

    private Product(final Integer id,
                    final String name,
                    final Double price,
                    final String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

    public Product(String name, Double price, String unit) {
        this(0, name, price, unit);
    }

    public Product() {
        this(0, "default-name", 0.0, "default-unit");
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Double price() {
        return price;
    }

    public String unit() {
        return unit;
    }

    public Product id(Integer newId) {
        return new Product(newId, name, price, unit);
    }
}

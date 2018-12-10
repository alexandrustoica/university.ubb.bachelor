package store.domain;


import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@ToString
@Immutable
@Table(name = "Product")
@EqualsAndHashCode(of={"id"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;

    @Column(name = "name")
    private final String name;

    @Column(name = "price")
    private final Double price;

    @Column(name = "unit")
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

package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import store.exception.UnavailableStockException;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Immutable
@ToString()
@Table(name = "Stock")
@EqualsAndHashCode(of = {"product", "quantity"})
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;

    @OneToOne(targetEntity = Product.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private final Product product;

    @Column(name = "quantity")
    private final Integer quantity;

    public Stock() {
        this(0, new Product(), 0);
    }

    public Stock(final Integer id,
                 final Product product,
                 final Integer quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Stock(final Product product,
                 final Integer quantity) {
        this(0, product, quantity);
    }

    public Product product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }

    public Stock quantity(Integer quantity) {
        return new Stock(product, quantity);
    }

    public Stock reduceQuantity(Integer quantity) {
        if (this.quantity < quantity)
            throw new UnavailableStockException();
        return new Stock(product, this.quantity - quantity);
    }
}

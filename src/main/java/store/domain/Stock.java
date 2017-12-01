package store.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Immutable
@ToString()
@EqualsAndHashCode(of={"product", "quantity"})
public class Stock {

    private Product product;
    private Integer quantity;

    public Stock(final Product product,
                 final Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }

    public Stock reduceQuantity(Integer quantity) {
        return new Stock(product, this.quantity - quantity);
    }
}

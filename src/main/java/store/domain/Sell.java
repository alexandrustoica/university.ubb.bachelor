package store.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "Sell")
@ToString(exclude = {"date"})
@EqualsAndHashCode(exclude = {"date"})
public class Sell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;

    @Column(name = "date")
    private final Calendar date;

    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private final Product product;

    @Column(name = "quantity")
    private final Integer quantity;

    public Sell() {
        this(0, Calendar.getInstance(), new Product(), 0);
    }

    public Sell(final Integer id,
                final Calendar date,
                final Product product,
                final Integer quantity) {
        this.id = id;
        this.date = date;
        this.product = product;
        this.quantity = quantity;
    }

    public Sell(final Calendar date,
                final Product product,
                final Integer quantity) {
        this(0, date, product, quantity);
    }

    @SuppressWarnings("unused")
    public Calendar date() {
        return date;
    }

    public Product product() {
        return product;
    }

    public Integer quantity() {
        return quantity;
    }
}

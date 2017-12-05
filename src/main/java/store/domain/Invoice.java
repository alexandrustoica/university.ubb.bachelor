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
@EqualsAndHashCode
@Table(name = "Invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Integer id;

    @Column(name = "name")
    private final String name;

    @OneToOne(targetEntity = Sell.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sell_id")
    private final Sell sell;

    @Column(name = "total")
    private final Double total;

    public Invoice() {
        this(0, "default", new Sell(), 0.0);
    }

    public Invoice(final Integer id, final String name, final Sell sell, final Double total) {
        this.id = id;
        this.name = name;
        this.sell = sell;
        this.total = total;
    }
    public Invoice(final String name, final Sell sell, final Double total) {
        this(0, name, sell, total);
    }

    @SuppressWarnings("unused")
    public String name() {
        return name;
    }

    public Sell sell() {
        return sell;
    }

    public Double total() {
        return total;
    }
}

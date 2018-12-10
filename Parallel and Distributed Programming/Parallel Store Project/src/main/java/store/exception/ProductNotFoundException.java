package store.exception;


import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Product was not found!");
    }
}

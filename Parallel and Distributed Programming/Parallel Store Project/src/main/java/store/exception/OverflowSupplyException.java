package store.exception;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public class OverflowSupplyException extends RuntimeException {
    public OverflowSupplyException() {
        super("Overflow Supply!");
    }
}

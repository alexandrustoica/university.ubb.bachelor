package store.exception;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@ToString
@EqualsAndHashCode(callSuper = false)
public final class UnavailableStockException extends RuntimeException {
    public UnavailableStockException() {
        super("404 Unavailable Stock!");
    }
}

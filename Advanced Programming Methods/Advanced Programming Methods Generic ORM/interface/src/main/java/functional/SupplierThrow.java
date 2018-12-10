package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface SupplierThrow<T, E extends Throwable> {
    T accept() throws E;
}
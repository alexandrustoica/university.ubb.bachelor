package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface Performer<R, T, E extends Throwable> {
    R apply(T param) throws E;
}
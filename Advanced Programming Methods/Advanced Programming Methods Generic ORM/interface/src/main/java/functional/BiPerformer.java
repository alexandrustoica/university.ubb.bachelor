package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface BiPerformer<R, T, U, E extends Throwable> {
    R apply(T param1, U param2) throws E;
}
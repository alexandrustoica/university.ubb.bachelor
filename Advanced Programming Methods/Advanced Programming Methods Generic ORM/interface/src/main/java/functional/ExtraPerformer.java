package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface ExtraPerformer<R, T, X,  U, E extends Throwable> {
    R apply(T param1, U param2, X param3) throws E;
}
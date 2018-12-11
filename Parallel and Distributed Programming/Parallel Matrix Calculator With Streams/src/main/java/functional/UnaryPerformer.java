package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface UnaryPerformer<R, T, E extends Exception> {

    /**
     * Applies a function with one parameter [unary function]
     * and in case something goes wrong throws an exception.
     *
     * @param param - the function's parameter [type T]
     * @return - the result [type R]
     * @throws E - the exception [type E]
     */
    R apply(T param) throws E;
}

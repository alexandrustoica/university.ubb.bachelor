package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface NullPerformer<R, E extends Exception> {

    /**
     * Applies a nullary function (function with 0 parameters) and if something goes wrong throws an exception.
     *
     * @return - the result [type R]
     * @throws E - the exception [type E]
     */
    R apply() throws E;
}

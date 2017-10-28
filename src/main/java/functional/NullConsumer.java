package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface NullConsumer<E extends Exception> {

    /**
     * Applies a nullary function (function with 0 parameters) and if something goes wrong throws an exception.
     * @throws E - the exception [type E]
     */
    void accept() throws E;
}

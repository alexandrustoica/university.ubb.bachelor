package functional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@FunctionalInterface
public interface UnaryConsumer<T, E extends Exception> {

    /**
     * Runs an unary consumer and if something goes wrong throws an exception.
     *
     * @param param - the consumer's parameter [type T]
     * @throws E - the consumer's exception [type E]
     */
    void accept(T param) throws E;
}

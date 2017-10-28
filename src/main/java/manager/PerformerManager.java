package manager;

import functional.NullConsumer;
import functional.NullPerformer;
import functional.UnaryConsumer;
import functional.UnaryPerformer;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class PerformerManager {

    private Consumer<Exception> handler;

    /**
     * Helps other objects to handle possible exceptions.
     *
     * @param handler - a consumer that accepts the exception.
     */
    public PerformerManager(Consumer<Exception> handler) {
        this.handler = handler;
    }

    /**
     * Performs an action (function with one parameter / unary function)
     * and takes care of the exception if that occurs.
     *
     * @param performer - the unary function
     * @param parameter - the parameter of the unary function
     * @param <R>       - the type of the result [return type]
     * @param <T>       - the type of the parameter [parameter type]
     * @param <E>       - the type of the exception [exception type]
     * @return - the action's result
     */
    public <R, T, E extends Exception> Optional<R>
    perform(UnaryPerformer<R, T, E> performer, T parameter) {
        try {
            return Optional.ofNullable(performer.apply(parameter));
        } catch (Exception exception) {
            handler.accept(exception);
            return Optional.empty();
        }
    }

    /**
     * Performs an action (function with 0 parameters / nullary function)
     * and takes care of the exception if that occurs.
     *
     * @param performer - the nullary function
     * @param <R>       - the type of the result [return type]
     * @param <E>       - the type of the exception [exception type]
     * @return - the action's result
     */
    public <R, E extends Exception> Optional<R>
    perform(NullPerformer<R, E> performer) {
        try {
            return Optional.ofNullable(performer.apply());
        } catch (Exception exception) {
            handler.accept(exception);
            return Optional.empty();
        }
    }

    /**
     * Performs a method (unary consumer/method) and takes care of the exception if that occurs.
     *
     * @param consumer  - the unary method
     * @param parameter - the parameter of the unary method
     * @param <T>       - the type of the parameter [parameter type]
     * @param <E>       - the type of the exception [exception type]
     */
    public <T, E extends Exception> void
    consume(UnaryConsumer<T, E> consumer, T parameter) {
        try {
            consumer.accept(parameter);
        } catch (Exception exception) {
            handler.accept(exception);
        }
    }

    public <E extends Exception> void
    consume(NullConsumer<E> consumer) {
        try {
            consumer.accept();
        } catch (Exception exception) {
            handler.accept(exception);
        }
    }
}

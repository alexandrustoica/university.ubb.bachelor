package manager;

import functional.BiPerformer;
import functional.Performer;
import functional.SupplierThrow;

import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public abstract class PerformerManager {

    protected abstract void handler(Throwable exception);

    protected  <R, E extends Throwable> Optional<R>
    perform(SupplierThrow<R, E> performer) {
        try {
            return Optional.ofNullable(performer.accept());
        } catch (Throwable exception) {
            handler(exception);
            return Optional.empty();
        }
    }
    protected <R, T, U, E extends Throwable> Optional<R>
    perform(BiPerformer<R, T, U, E> performer, T param1, U param2) {
        try {
            return Optional.ofNullable(performer.apply(param1, param2));
        } catch (Throwable exception) {
            handler(exception);
            return Optional.empty();
        }
    }

    protected  <R, T, E extends Throwable> Optional<R>
    perform(Performer<R, T, E> performer, T param) {
        try {
            return Optional.ofNullable(performer.apply(param));
        } catch (Throwable exception) {
            handler(exception);
            return Optional.empty();
        }
    }
}

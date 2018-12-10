package store.deposit;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Deposit<T, Id> {
    T deposit(T element);
    Optional<T> remove(T element);
    Optional<T> findById(Id id);
    List<T> elements();
}

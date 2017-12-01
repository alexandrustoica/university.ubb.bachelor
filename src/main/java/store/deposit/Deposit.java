package store.deposit;

import java.util.List;

public interface Deposit<T> {
    T deposit(final T element, final Integer quantity);
    void remove(final T element, final Integer quantity);
    Boolean isAvailable(final T element, final Integer quantity);
    T findById(final Integer id);
    List<T> all();
}

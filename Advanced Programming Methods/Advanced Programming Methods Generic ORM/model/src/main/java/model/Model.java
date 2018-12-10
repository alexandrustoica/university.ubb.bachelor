package model;

import domain.Idable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Model<T extends Idable<Id>, Id extends Serializable> {
    T insert(T element);
    T update(T element, T with);
    Optional<T> delete(T element);
    Optional<T> getElementById(Id id);
    List<T> all();
}

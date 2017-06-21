package model;

import domain.Idable;
import javafx.util.Pair;
import domain.RelationEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("unused")
public interface ModelManyToMany<T extends Idable<Id>, U extends Idable<Id>, Relation extends RelationEntity<T, U> & Idable<Id>, Id extends Serializable> {
    T insert(T element);
    T update(T element, T with);
    Optional<T> delete(T element);
    Optional<T> getElementById(Id id);
    List<T> all();
    U add(U element);
    U refresh(U element, U with);
    Optional<U> remove(U element);
    Optional<U> receiveElementById(Id id);
    List<U> every();
    Pair<Optional<T>, Optional<U>> insert(T left, U right);
    Pair<Optional<T>, Optional<U>> delete(T left, U right);
}



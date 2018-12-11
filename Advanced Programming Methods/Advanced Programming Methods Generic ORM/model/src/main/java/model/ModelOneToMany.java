package model;

import domain.Idable;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ModelOneToMany<One extends Idable<Id>, Many extends Idable<Id>, Id extends Serializable>
    extends Model<One, Id> {
    Optional<One> insert(One element, Many item);
    Optional<One> delete(One element, Many item);
    Optional<One> update(One element, Many item, Many with);
}


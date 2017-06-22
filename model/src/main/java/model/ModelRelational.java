package model;

import database.DatabaseSessionGateway;
import domain.Idable;
import repository.RepositoryEntity;
import repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelRelational<T extends Idable<Id>,  Id extends Serializable> implements Model<T, Id> {

    private final Repository<T, Id> repository;

    public ModelRelational(final Class<T> type, final DatabaseSessionGateway gateway) {
        repository = new RepositoryEntity<>(type, gateway);
    }

    @Override
    public T insert(T element) {
        return repository.insert(element);
    }

    @Override
    public Optional<T> delete(T element) {
        return repository.delete(element);
    }

    @Override
    public T update(T element, T with) {
        return repository.update(element, with);
    }

    @Override
    public Optional<T> getElementById(Id id) {
        return repository.getElementById(id);
    }

    @Override
    public List<T> all() {
        return repository.all();
    }
}

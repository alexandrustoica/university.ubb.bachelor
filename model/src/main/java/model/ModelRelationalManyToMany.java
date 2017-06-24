package model;

import database.DatabaseGateway;
import domain.Idable;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import repository.RepositoryEntity;
import repository.Repository;
import domain.RelationEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelRelationalManyToMany<T extends Idable<Id>, U extends Idable<Id>, Relation extends RelationEntity<T, U> & Idable<Id>, Id extends Serializable>
        implements ModelManyToMany<T, U, Relation, Id> {

    private final Model<U, Id> modelU;
    private final Model<T, Id> modelT;
    private final Repository<Relation, Id> repositoryRelation;
    private final Class<Relation> type;
    private static Logger logger;

    public ModelRelationalManyToMany(final Class<T> typeT,
                                     final Class<U> typeU,
                                     final Class<Relation> typeRelation,
                                     final DatabaseGateway loader) {
        modelT = new ModelRelational<>(typeT, loader);
        modelU = new ModelRelational<>(typeU, loader);
        type = typeRelation;
        logger = Logger.getLogger(ModelRelationalManyToMany.class);
        repositoryRelation = new RepositoryEntity<>(typeRelation, loader);
    }

    @Override public T insert(T element) {
        return modelT.insert(element);
    }

    @Override public T update(T element, T with) {
        return modelT.update(element, with);
    }

    @Override public Optional<T> delete(T element) {
        return modelT.delete(element);
    }

    @Override public Optional<T> getElementById(Id id) {
        return modelT.getElementById(id);
    }

    @Override public List<T> all() {
        return modelT.all();
    }

    @Override
    public U add(U element) {
        return modelU.insert(element);
    }

    @Override
    public U refresh(U element, U with) {
        return modelU.update(element, with);
    }

    @Override
    public Optional<U> remove(U element) {
        return modelU.delete(element);
    }

    @Override
    public Optional<U> receiveElementById(Id id) {
        return modelU.getElementById(id);
    }

    @Override
    public List<U> every() {
        return modelU.all();
    }

    @Override
    public List<T> allFrom(U element) {
        return repositoryRelation.all().stream()
                .filter(item -> item.supplyU().getId().equals(element.getId()))
                .map(RelationEntity::supplyT)
                .collect(Collectors.toList());
    }

    @Override
    public List<U> everyFrom(T element) {
        return repositoryRelation.all().stream()
                .filter(item -> item.supplyT().getId().equals(element.getId()))
                .map(RelationEntity::supplyU)
                .collect(Collectors.toList());
    }

    @Override
    public Pair<Optional<T>, Optional<U>> insert(T left, U right) {
        try {
            insertRelation(left, right);
        } catch (InstantiationException | IllegalAccessException exception) {
            logger.error(exception);
            return new Pair<>(Optional.empty(), Optional.empty());
        }
        return new Pair<>(modelT.getElementById(left.getId()), modelU.getElementById(right.getId()));
    }

    @SuppressWarnings("unchecked")
    private void insertRelation(T left, U right) throws InstantiationException, IllegalAccessException {
        if (!existsRelation(left, right)  && existsElements(left, right)) {
            Relation relation = (Relation) type.newInstance().create(left, right);
            repositoryRelation.insert(relation);
        }
    }

    private boolean existsElements(T left, U right) {
        return !left.getId().equals(0) && !right.getId().equals(0);
    }

    private Boolean existsRelation(T left, U right) {
        return repositoryRelation.all().stream().anyMatch(relation -> relation.supplyT().equals(left) && relation.supplyU().equals(right));
    }

    @Override
    public Pair<Optional<T>, Optional<U>> delete(T left, U right) {
        if(!existsRelation(left, right)) {
            return new Pair<>(Optional.empty(), Optional.empty());
        }
        repositoryRelation.all().stream()
                .filter(relation -> relation.supplyT().equals(left) && relation.supplyU().equals(right))
                .findFirst()
                .ifPresent(repositoryRelation::delete);
        return new Pair<>(modelT.getElementById(left.getId()), modelU.getElementById(right.getId()));
    }
}

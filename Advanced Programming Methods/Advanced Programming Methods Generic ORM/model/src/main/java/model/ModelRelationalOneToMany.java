package model;

import database.DatabaseGateway;
import domain.Idable;
import repository.RepositoryEntity;
import repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelRelationalOneToMany<One extends Idable<Id>, Many extends Idable<Id>, Id extends Serializable>
        extends ModelRelational<One, Id> implements ModelOneToMany<One, Many, Id> {

    private final Repository<Many, Id> repositoryRelation;
    private final ConfigurationModel<One, Many> configuration;

    public ModelRelationalOneToMany(final ConfigurationModel<One, Many> configuration,
                                     final Class<One> typeOne,
                                     final Class<Many> typeMany,
                                     final DatabaseGateway loader) {
        super(typeOne, loader);
        this.configuration = configuration;
        repositoryRelation = new RepositoryEntity<>(typeMany, loader);
    }

    @Override
    public Optional<One> insert(One element, Many item) {
        Optional<One> result = getElementById(element.getId());
        result.ifPresent(one -> {
            configuration.consume(element, item);
            repositoryRelation.insert(item);
        });
        return getElementById(element.getId());
    }

    @Override
    public Optional<One> delete(One element, Many item) {
        if (validRelationshipBetween(element, item)) {
            repositoryRelation.delete(item);
        }
        return getElementById(element.getId());
    }

    @Override
    public Optional<One> update(One element, Many item, Many with) {
        if (validRelationshipBetween(element, item)) {
            repositoryRelation.update(item, with);
        }
        return getElementById(element.getId());
    }

    private boolean validRelationshipBetween(One element, Many item) {
        return configuration.supply(item) != null && configuration.supply(item).equals(element);
    }
}

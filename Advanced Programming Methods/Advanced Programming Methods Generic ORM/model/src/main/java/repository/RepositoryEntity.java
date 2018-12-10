package repository;

import database.DatabaseGateway;
import domain.Idable;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RepositoryEntity<T extends Idable<Id>, Id extends Serializable> implements Repository<T, Id> {

    private final DatabaseGateway gateway;
    private final Class<T> type;

    public RepositoryEntity(final Class<T> type, final DatabaseGateway gateway) {
        this.gateway = gateway;
        this.type = type;
    }

    @Override
    public T insert(T element) {
        Session session = gateway.openSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        return element;
    }

    @Override
    public T update(T element, T with) {
        Session session = gateway.openSession();
        with.setId(element.getId());
        session.beginTransaction();
        session.update(with);
        session.getTransaction().commit();
        session.close();
        return with;
    }

    @Override
    public Optional<T> delete(T element) {
        Session session = gateway.openSession();
        session.beginTransaction();
        Optional<T> result = this.getElementById(element.getId());
        result.ifPresent(session::delete);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> all() {
        Session session = gateway.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);
        criteriaQuery.select(root);
        Query resultQuery = session.createQuery(criteriaQuery);
        List<T> result = resultQuery.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<T> getElementById(Id id) {
        Session session = gateway.openSession();
        T element = session.get(type, id);
        session.close();
        return Optional.ofNullable(element);
    }
}

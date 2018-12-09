package model;

import error.Errors;
import repository.RepositoryEntityProtocol;

import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Model<T, ID>
        extends ModelDatabase
        implements ModelProtocol<T, ID> {

    protected RepositoryEntityProtocol repository;

    @Override
    @SuppressWarnings("unchecked")
    public int add(T element) {
        try {
            return repository.add(element);
        } catch (Errors errors) {
            handleErrors(errors);
            return 0;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(T element, T with) {
        try {
            repository.update(element, with);
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void delete(T element) {
        try {
            repository.delete(element);
        } catch (Errors errors) {
            handleErrors(errors);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<T> getAll() {
        try {
            return repository.getAll();
        } catch (Errors errors) {
            handleErrors(errors);
            return new ArrayList<>();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getElementById(ID id) {
        try {
            return (T)repository.findElementById(id);
        } catch (Errors errors) {
            handleErrors(errors);
            return null;
        }
    }

}
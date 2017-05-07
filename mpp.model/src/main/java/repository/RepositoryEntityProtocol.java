package repository;

import error.Errors;
import java.util.ArrayList;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RepositoryEntityProtocol<T, ID> {

    int add(T element) throws Errors;
    void update(T element, T with) throws Errors;
    void delete(T element) throws Errors;
    ArrayList<T> getAll() throws Errors;
    T findElementById(ID id) throws Errors;

}
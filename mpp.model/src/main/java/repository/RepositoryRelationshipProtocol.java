package repository;

import error.Errors;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RepositoryRelationshipProtocol<T, E, TID, EID> {

    void add(T element, E other) throws Errors;
    void delete(T element, E other) throws Errors;
    ArrayList<T> getTObjectsByID(EID id) throws Errors;
    ArrayList<E> getEObjectsByID(TID id) throws Errors;

}
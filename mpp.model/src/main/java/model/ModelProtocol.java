package model;

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

public interface ModelProtocol<T, ID> {

    int add(T element);
    void update(T element, T with);
    void delete(T element);
    ArrayList<T> getAll();
    T getElementById(ID id);

}
package model;

import java.util.ArrayList;

/**
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
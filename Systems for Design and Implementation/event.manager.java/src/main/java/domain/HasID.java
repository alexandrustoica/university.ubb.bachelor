package domain;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface HasID<ID> extends Serializable {

    ID getID();
    void setID(ID id);

}
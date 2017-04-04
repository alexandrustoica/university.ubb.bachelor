package domain;

/**
 * Name:        HasID
 * Effect:      Interface
 * Date:        01/04/2017
 * @author      Alexandru Stoica
 * @version     1.0
 */

public interface HasID<ID> {

    ID getID();
    void setID(ID id);

}
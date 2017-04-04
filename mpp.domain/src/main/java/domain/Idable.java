package domain;

/**
 * Name:        Idable
 * Effect:      Describes the id-able objects in the domain.
 * Date:        01/04/2017
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class Idable<ID> implements HasID<ID> {

    protected ID id;

    @Override
    public ID getID() {
        return id;
    }

    @Override
    public void setID(ID id) {
        this.id = id;
    }

}
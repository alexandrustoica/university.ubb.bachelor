package domain;

/**
 * @author Alexandru Stoica
 * @version 1.0
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
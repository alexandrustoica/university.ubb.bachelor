package transfarable;

import domain.Idable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Project implements Serializable, Idable<Integer> {

    private Integer id;
    private String name;

    public Project(String text) {
        this(0, text);
    }

    public Project(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id.toString() + " " + name;
    }
}

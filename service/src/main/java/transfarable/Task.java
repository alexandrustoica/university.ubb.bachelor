package transfarable;

import domain.Idable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Task implements Serializable, Idable<Integer> {

    private Integer id;
    private String text;

    public Task(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Task(String text) {
        this(0, text);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return id.toString() + " " + text;
    }
}

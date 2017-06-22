package transfarable;

import domain.Idable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Notification implements Serializable, Idable<Integer> {

    private Integer id;
    private String text;

    public Notification(Integer id, String text) {
        this.id = id;
        this.text = text;
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
}

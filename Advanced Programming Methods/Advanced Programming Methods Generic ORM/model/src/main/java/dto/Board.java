package dto;

import domain.Idable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Board implements Serializable, Idable<Integer> {

    private Integer id;
    private String url;
    private String text;

    public Board() {
        this(0, "", "");
    }

    public Board(Integer id, String url, String text) {
        this.id = id;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
package dto;

import domain.Idable;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class User implements Serializable, Idable<Integer> {

    private Integer id;
    private String username;
    private String password;

    public User() {
        this(0, "", "");
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
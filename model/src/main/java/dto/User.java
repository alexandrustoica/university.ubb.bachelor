package dto;

import domain.Idable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class User implements Idable<Integer> {

    private Integer id;
    private String username;
    private String password;

    private final static Integer DEFAULT_ID = 0;

    public User(String username, String password) {
        this(DEFAULT_ID, username, password);
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

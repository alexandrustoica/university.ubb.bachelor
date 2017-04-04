package domain;

import java.io.Serializable;

/**
 * Name:        User
 * Effect:      Describes the user entity.
 * Date:        01/04/2017
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class User extends Idable<Integer> implements Serializable {

    private String name;
    private String password;

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this(0, name, password);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isEqual(User other) {
        return this.id.equals(other.id) &&
                this.password.equals(other.password) &&
                this.name.equals(other.name);
    }

}
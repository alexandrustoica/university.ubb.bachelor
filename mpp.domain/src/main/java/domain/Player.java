package domain;

import java.io.Serializable;

/**
 * Name:        Player
 * Effect:      Describes the player entity.
 * Date:        01/04/2017
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class Player extends Idable<Integer> implements Serializable {

    private String name;
    private Integer age;

    public Player(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Player(String name, Integer age) {
        this(0, name, age);
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge() {
        return this.age;
    }

    public Boolean isEqual(Player other) {
        return this.age.equals(other.age) && this.id.equals(other.id) && this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return this.name + " " + this.age.toString();
    }

}
package request;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestAddPlayer implements RequestProtocol {

    private String name;
    private Integer age;
    private ArrayList<Integer> events;

    public RequestAddPlayer(String name, Integer age, ArrayList<Integer> events) {
        this.name = name;
        this.age = age;
        this.events = events;
    }

    @Override
    public RequestType getType() {
        return RequestType.ADD_PLAYER;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getEvents() {
        return events;
    }
}

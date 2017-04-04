package domain;

import java.io.Serializable;

/**
 * Name:        Event
 * Effect:      Describes the event entity.
 * Date:        01/04/2017
 * @author      Alexandru Stoica
 * @version     1.0
 */

public class Event extends Idable<Integer> implements Serializable {

    private Integer distance;
    private EventStyle style;

    public Event(Integer distance, EventStyle style) {
        this(0, distance, style);
    }

    public Event(Integer id, Integer distance, EventStyle style) {
        this.id = id;
        this.distance = distance;
        this.style = style;
    }

    public Integer getDistance() {
        return distance;
    }

    public EventStyle getStyle() {
        return style;
    }

    public Boolean isEqual(Event other) {
        return other.distance.equals(this.distance) &&
                other.id.equals(this.id) &&
                other.style.equals(this.style);
    }

    @Override
    public String toString() {
        return this.distance.toString() + " " + this.style.toString();
    }

}
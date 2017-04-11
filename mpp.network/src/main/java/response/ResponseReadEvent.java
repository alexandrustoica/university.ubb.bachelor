package response;

import domain.Event;
import domain.Player;
import error.Errors;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseReadEvent implements ResponseProtocol {

    private ArrayList<Event> events;

    private Errors errors;

    public ResponseReadEvent(Errors errors) {
        this.errors = errors;
    }

    public ResponseReadEvent(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.READ_EVENT;
    }

    public Errors getErrors() {
        return errors;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }
}

package transferable;

import domain.Event;
import domain.Player;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class GetEventResponse implements ResponseProtocol {

    private ArrayList<Event> listEvents;

    public GetEventResponse (ArrayList<Event> listEvents) {
        this.listEvents = listEvents;

    }

    @Override
    public ResponseType getType() {
        return ResponseType.GET_EVENT;
    }

    public ArrayList<Event> getListEvents() {
        return listEvents;
    }
}

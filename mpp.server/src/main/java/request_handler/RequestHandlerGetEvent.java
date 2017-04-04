package request_handler;

import domain.Event;
import domain.EventStyle;
import domain.Player;
import model.ModelEvent;
import model.ModelPlayer;
import transferable.*;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */
public class RequestHandlerGetEvent
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol {

    private GetEventRequest request;

    public RequestHandlerGetEvent(GetEventRequest request) {
        this.request = request;
    }

    @Override
    public ResponseProtocol solve() {
        ModelPlayer modelPlayer = new ModelPlayer(getDatabaseURL());
        ModelEvent modelEvent = new ModelEvent(getDatabaseURL());
        GetEventResponse response = new GetEventResponse(modelEvent.getAll());
        if (request.getIdPlayer() != 0) {
            response = new GetEventResponse(modelPlayer.getEventsFromPlayer(request.getIdPlayer()));
        }
        return response;
    }

}

package response_handler;

import domain.Event;
import domain.Player;
import observer.Observable;
import observer.ObserverApplicationProtocol;
import transferable.GetEventResponse;
import transferable.GetPlayerResponse;

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

public class ResponseHandlerGetEvent
        extends Observable<ObserverApplicationProtocol>
        implements InternalResponseHandlerProtocol {

    private GetEventResponse response;

    public ResponseHandlerGetEvent(GetEventResponse response) {
        this.response = response;
    }

    @Override
    public void solve() {
        ArrayList<Event> result = response.getListEvents();
        observers.forEach(observer ->
                observer.notifyGetEvents(result));
    }
}

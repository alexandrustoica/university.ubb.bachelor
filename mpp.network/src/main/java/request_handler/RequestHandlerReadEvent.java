package request_handler;

import domain.Event;
import domain.EventStyle;
import domain.Player;
import error.Errors;
import model.ModelEvent;
import model.ModelPlayer;
import observer.Observable;
import observer.ObserverServerProtocol;
import request.RequestReadEvent;
import request.RequestReadPlayer;
import response.ResponseProtocol;
import response.ResponseReadEvent;
import response.ResponseReadPlayer;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerReadEvent
        extends Observable<ObserverServerProtocol>
        implements InternalRequestHandlerProtocol {

    private RequestReadEvent request;
    private Errors errors;

    @SuppressWarnings("all")
    public RequestHandlerReadEvent(RequestReadEvent request) {
        this.request = request;
        this.errors = new Errors();
    }

    @Override
    public ResponseProtocol solve() {
        ModelPlayer modelPlayer = new ModelPlayer(RequestHandlerDatabase.getDatabaseURL());
        ModelEvent modelEvent = new ModelEvent(RequestHandlerDatabase.getDatabaseURL());
        if (request.getIdPlayer() == 0) {
            return new ResponseReadEvent(modelEvent.getAll());
        }
        return new ResponseReadEvent(modelPlayer.getEventsFromPlayer(request.getIdPlayer()));
    }

}

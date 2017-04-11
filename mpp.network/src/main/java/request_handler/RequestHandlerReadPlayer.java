package request_handler;

import error.Errors;
import model.ModelEvent;
import model.ModelPlayer;
import observer.Observable;
import observer.ObserverServerProtocol;
import request.RequestReadPlayer;
import response.ResponseProtocol;
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

public class RequestHandlerReadPlayer
        extends Observable<ObserverServerProtocol>
        implements InternalRequestHandlerProtocol {

    private RequestReadPlayer request;
    private Errors errors;

    @SuppressWarnings("all")
    public RequestHandlerReadPlayer(RequestReadPlayer request) {
        this.request = request;
        this.errors = new Errors();
    }

    @Override
    public ResponseProtocol solve() {
        ModelPlayer modelPlayer = new ModelPlayer(RequestHandlerDatabase.getDatabaseURL());
        ModelEvent modelEvent = new ModelEvent(RequestHandlerDatabase.getDatabaseURL());
        if (request.getIdEvent() == 0) {
            return new ResponseReadPlayer(modelPlayer.getAll());
        }
        return new ResponseReadPlayer(modelEvent.getPlayersFromEvent(request.getIdEvent()));
    }

}

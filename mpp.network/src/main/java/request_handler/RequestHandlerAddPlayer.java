package request_handler;

import domain.Player;
import domain.User;
import error.Error;
import error.Errors;
import model.ModelEvent;
import model.ModelPlayer;
import model.ModelUser;
import observer.Observable;
import observer.ObserverServerProtocol;
import request.RequestAddPlayer;
import request.RequestLogin;
import response.NotificationType;
import response.ResponseLogin;
import response.ResponseNotification;
import response.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerAddPlayer
        extends Observable<ObserverServerProtocol>
        implements InternalRequestHandlerProtocol {

    private RequestAddPlayer request;
    private Errors errors;

    @SuppressWarnings("all")
    public RequestHandlerAddPlayer(RequestAddPlayer request) {
        this.request = request;
        this.errors = new Errors();
    }

    @Override
    public ResponseProtocol solve() {
        ModelPlayer modelPlayer = new ModelPlayer(RequestHandlerDatabase.getDatabaseURL());
        ModelEvent modelEvent = new ModelEvent(RequestHandlerDatabase.getDatabaseURL());
        Player player = new Player(request.getName(), request.getAge());
        int id = modelPlayer.add(player);
        request.getEvents().forEach(idEvent -> modelEvent.addPlayerToEvent(id, idEvent));
        observers.forEach(observerServerProtocol -> {
            observerServerProtocol.notifyAllUsers(new ResponseNotification(NotificationType.UPDATE));
        });
        return new ResponseNotification(NotificationType.UPDATE);
    }

}

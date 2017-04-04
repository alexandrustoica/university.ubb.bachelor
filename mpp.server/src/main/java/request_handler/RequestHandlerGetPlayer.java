package request_handler;

import domain.Player;
import model.ModelEvent;
import model.ModelPlayer;
import transferable.GetPlayerRequest;
import transferable.GetPlayerResponse;
import transferable.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */
public class RequestHandlerGetPlayer
        extends RequestHandlerDatabase
        implements InternalRequestHandlerProtocol {

    private GetPlayerRequest request;

    public RequestHandlerGetPlayer(GetPlayerRequest request) {
        this.request = request;
    }

    @Override
    public ResponseProtocol solve() {
        ModelPlayer modelPlayer = new ModelPlayer(getDatabaseURL());
        ModelEvent modelEvent = new ModelEvent(getDatabaseURL());
        GetPlayerResponse response = new GetPlayerResponse(modelPlayer.getAll());
        if (request.getIdEvent() != 0) {
            response = new GetPlayerResponse(modelEvent.getPlayersFromEvent(request.getIdEvent()));
        }
        return response;
    }

}

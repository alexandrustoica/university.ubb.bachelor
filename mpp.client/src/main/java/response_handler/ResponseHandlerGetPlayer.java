package response_handler;

import domain.Player;
import observer.Observable;
import observer.ObserverApplicationProtocol;
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

public class ResponseHandlerGetPlayer
        extends Observable<ObserverApplicationProtocol>
        implements InternalResponseHandlerProtocol {

    private GetPlayerResponse response;

    public ResponseHandlerGetPlayer(GetPlayerResponse response) {
        this.response = response;
    }

    @Override
    public void solve() {
        ArrayList<Player> result = response.getListPlayers();
        observers.forEach(observer ->
                observer.notifyGetPlayers(result));
    }
}

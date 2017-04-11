package response_handler;

import observer.Observable;
import observer.ObserverConnectionProtocol;
import response.ResponseReadPlayer;
import response.ResponseSignUp;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandlerReadPlayer
        extends Observable<ObserverConnectionProtocol>
        implements InternalResponseHandlerProtocol {

    private ResponseReadPlayer response;

    @SuppressWarnings("all")
    public ResponseHandlerReadPlayer(ResponseReadPlayer response) {
        this.response = response;
    }

    @Override
    @SuppressWarnings("all")
    public void solve() {
        if(response.getErrors() == null) {
            observers.forEach(observer -> observer.notify(response.getPlayers()));
        } else {
            observers.forEach(observer -> observer.notifyErrors(response.getErrors()));
        }
    }

}

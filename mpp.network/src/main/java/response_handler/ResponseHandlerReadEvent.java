package response_handler;

import observer.Observable;
import observer.ObserverConnectionProtocol;
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

public class ResponseHandlerReadEvent
        extends Observable<ObserverConnectionProtocol>
        implements InternalResponseHandlerProtocol {

    private ResponseReadEvent response;

    @SuppressWarnings("all")
    public ResponseHandlerReadEvent(ResponseReadEvent response) {
        this.response = response;
    }

    @Override
    @SuppressWarnings("all")
    public void solve() {
        if(response.getErrors() == null) {
            observers.forEach(observer -> observer.notify(response.getEvents()));
        } else {
            observers.forEach(observer -> observer.notifyErrors(response.getErrors()));
        }
    }

}

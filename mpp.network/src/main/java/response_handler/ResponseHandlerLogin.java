package response_handler;

import observer.Observable;
import observer.ObserverConnectionProtocol;
import response.ResponseLogin;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandlerLogin
        extends Observable<ObserverConnectionProtocol>
        implements InternalResponseHandlerProtocol {

    private ResponseLogin response;

    @SuppressWarnings("all")
    public ResponseHandlerLogin(ResponseLogin response) {
        this.response = response;
    }

    @Override
    @SuppressWarnings("all")
    public void solve() {
        if(response.getErrors() == null) {
            observers.forEach(observer -> observer.notify(response.getUser()));
        } else {
            observers.forEach(observer -> observer.notifyErrors(response.getErrors()));
        }
    }

}

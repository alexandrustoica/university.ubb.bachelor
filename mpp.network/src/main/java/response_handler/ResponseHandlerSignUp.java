package response_handler;

import observer.Observable;
import observer.ObserverConnectionProtocol;
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

public class ResponseHandlerSignUp
        extends Observable<ObserverConnectionProtocol>
        implements InternalResponseHandlerProtocol {

    private ResponseSignUp response;

    @SuppressWarnings("all")
    public ResponseHandlerSignUp(ResponseSignUp response) {
        this.response = response;
    }

    @Override
    @SuppressWarnings("all")
    public void solve() {
        if(response.getErrors().getErrors().isEmpty()) {
            observers.forEach(observer -> observer.notify(response.getUser()));
        } else {
            observers.forEach(observer -> observer.notifyErrors(response.getErrors()));
        }
    }

}

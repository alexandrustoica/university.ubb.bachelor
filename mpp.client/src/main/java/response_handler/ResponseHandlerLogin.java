package response_handler;

import error.Errors;
import error.Error;
import observer.Observable;
import observer.ObserverAuthenticationProtocol;
import transferable.LoginResponse;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandlerLogin
        extends Observable<ObserverAuthenticationProtocol>
        implements InternalResponseHandlerProtocol {

    private LoginResponse response;

    public ResponseHandlerLogin(LoginResponse response) {
        this.response = response;
    }

    @Override
    public void solve() {
        if (response.getUser() == null) {
            observers.forEach(observer ->
                    observer.notifyErrors(new Errors(new Error("Unable to login!"))));
        }
        else {
            observers.forEach(observer ->
                    observer.notifySuccess(response.getUser()));
        }
    }

}

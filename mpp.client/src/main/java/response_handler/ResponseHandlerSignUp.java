package response_handler;

import error.Error;
import error.Errors;
import observer.Observable;
import observer.ObserverAuthenticationProtocol;
import transferable.SignUpResponse;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandlerSignUp
        extends Observable<ObserverAuthenticationProtocol>
        implements InternalResponseHandlerProtocol {

    private SignUpResponse response;

    public ResponseHandlerSignUp(SignUpResponse response) {
        this.response = response;
    }

    @Override
    public void solve() {
        if (response.getUser() == null) {
            observers.forEach(observer ->
                    observer.notifyErrors(new Errors(new Error("Unable to sign up!"))));
        }
        else {
            observers.forEach(observer ->
                    observer.notifySuccess(response.getUser()));
        }
    }
}

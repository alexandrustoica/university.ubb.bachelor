package response_handler;

import observer.ObserverConnectionProtocol;
import response.ResponseLogin;
import response.ResponseNotification;
import response.ResponseProtocol;
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

public class ResponseHandlerFactory {

    public static InternalResponseHandlerProtocol getHandler(ResponseProtocol response, ObserverConnectionProtocol observer) {
        switch (response.getType()) {
            case SIGN_UP:
                ResponseHandlerSignUp responseHandlerSignUp =
                        new ResponseHandlerSignUp((ResponseSignUp) response);
                responseHandlerSignUp.addObserver(observer);
                return responseHandlerSignUp;
            case LOGIN:
                ResponseHandlerLogin responseHandlerLogin =
                        new ResponseHandlerLogin((ResponseLogin) response);
                responseHandlerLogin.addObserver(observer);
                return responseHandlerLogin;
            case NOTIFICATION:
                ResponseHandlerNotification responseHandlerNotification =
                        new ResponseHandlerNotification((ResponseNotification) response);
                responseHandlerNotification.addObserver(observer);
                return responseHandlerNotification;
            default:
                return new ResponseHandlerSignUp((ResponseSignUp) response);
        }
    }

}

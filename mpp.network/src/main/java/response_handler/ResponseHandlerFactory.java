package response_handler;

import observer.ObserverConnectionProtocol;
import response.*;

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
            case READ_PLAYER:
                ResponseHandlerReadPlayer responseHandlerReadPlayer =
                        new ResponseHandlerReadPlayer((ResponseReadPlayer) response);
                responseHandlerReadPlayer.addObserver(observer);
                return responseHandlerReadPlayer;
            case READ_EVENT:
                ResponseHandlerReadEvent responseHandlerReadEvent =
                        new ResponseHandlerReadEvent((ResponseReadEvent) response);
                responseHandlerReadEvent.addObserver(observer);
                return responseHandlerReadEvent;
            default:
                return new ResponseHandlerSignUp((ResponseSignUp) response);
        }
    }

}

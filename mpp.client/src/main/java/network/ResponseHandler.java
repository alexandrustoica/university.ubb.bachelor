package network;

import observer.ObserverApplicationProtocol;
import observer.ObserverAuthenticationProtocol;
import observer.ObserverProtocol;
import response_handler.ResponseHandlerGetEvent;
import response_handler.ResponseHandlerGetPlayer;
import response_handler.ResponseHandlerLogin;
import response_handler.ResponseHandlerSignUp;
import transferable.*;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandler implements ResponseHandleProtocol {

    private ObserverProtocol observer;

    @Override
    public void setObserver(ObserverProtocol observer) {
        this.observer = observer;
    }

    @Override
    public void handleResponse(ResponseProtocol response) {
        switch (response.getType()) {
            case LOGIN:
                ResponseHandlerLogin loginHandler =
                        new ResponseHandlerLogin((LoginResponse)response);
                loginHandler.addObserver((ObserverAuthenticationProtocol) observer);
                loginHandler.solve();
                break;
            case SIGNUP:
                ResponseHandlerSignUp signUpHandler =
                        new ResponseHandlerSignUp((SignUpResponse)response);
                signUpHandler.addObserver((ObserverAuthenticationProtocol) observer);
                signUpHandler.solve();
                break;
            case GET_PLAYER:
                ResponseHandlerGetPlayer getPlayerHandler =
                        new ResponseHandlerGetPlayer((GetPlayerResponse) response);
                getPlayerHandler.addObserver((ObserverApplicationProtocol) observer);
                getPlayerHandler.solve();
                break;
            case GET_EVENT:
                ResponseHandlerGetEvent getEventHandler =
                    new ResponseHandlerGetEvent((GetEventResponse) response);
                getEventHandler.addObserver((ObserverApplicationProtocol) observer);
                getEventHandler.solve();
                break;
            default:
                break;
        }
    }


}

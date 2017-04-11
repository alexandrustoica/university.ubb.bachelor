package request_handler;

import request.*;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestHandlerFactory {

    public static InternalRequestHandlerProtocol getHandler(RequestProtocol request) {
        switch(request.getType()) {
            case SIGN_UP:
                return new RequestHandlerSignUp((RequestSignUp) request);
            case LOGIN:
                return new RequestHandlerLogin((RequestLogin) request);
            case READ_PLAYER:
                return new RequestHandlerReadPlayer((RequestReadPlayer) request);
            case READ_EVENT:
                return new RequestHandlerReadEvent((RequestReadEvent) request);
            case ADD_PLAYER:
                return new RequestHandlerAddPlayer((RequestAddPlayer) request);
            default:
                return new RequestHandlerSignUp((RequestSignUp) request);
        }
    }

}

package request_handler;

import request.RequestLogin;
import request.RequestProtocol;
import request.RequestSignUp;

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
            default:
                return new RequestHandlerSignUp((RequestSignUp) request);
        }
    }

}

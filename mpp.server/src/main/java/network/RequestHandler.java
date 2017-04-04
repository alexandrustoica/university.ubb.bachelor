package network;

import request_handler.*;
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

public class RequestHandler implements RequestHandlerProtocol {

    @Override
    public ResponseProtocol handleRequest(RequestProtocol request) {
        InternalRequestHandlerProtocol handler;
        switch (request.getType()) {
            case LOGIN:
                handler = new RequestHandlerLogin((LoginRequest)request);
                return handler.solve();
            case SIGNUP:
                handler = new RequestHandlerSignUp((SignUpRequest)request);
                return handler.solve();
            case GET_PLAYER:
                handler = new RequestHandlerGetPlayer((GetPlayerRequest)request);
                return handler.solve();
            case GET_EVENT:
                handler = new RequestHandlerGetEvent((GetEventRequest)request);
                return handler.solve();
            case MESSAGE:
                ResponseProtocol response = new MessageResponse("Ana are mare!");
                return response;
            default:
                return null;
        }
    }

}


package response_handler;

import connection.ResponseHandler;
import observer.ObserverConnectionProtocol;
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
                ResponseHandlerSignUp responseHandler = new ResponseHandlerSignUp((ResponseSignUp) response);
                responseHandler.addObserver(observer);
                return responseHandler;
            default:
                return new ResponseHandlerSignUp((ResponseSignUp) response);
        }

    }

}

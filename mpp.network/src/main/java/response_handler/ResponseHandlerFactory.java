package response_handler;

import exception.ConnectionException;
import observer.ObserverConnectionProtocol;
import response.ResponseProtocol;

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

        return new InternalResponseHandlerProtocol() {
            @Override
            public void solve() {

            }
        };

    }

}

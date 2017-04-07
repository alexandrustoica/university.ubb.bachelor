package connection;

import request.RequestProtocol;
import request_handler.*;
import response.ResponseProtocol;

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
        InternalRequestHandlerProtocol handler = RequestHandlerFactory.getHandler(request);
        return handler.solve();
    }

}


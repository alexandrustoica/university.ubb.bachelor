package connection;

import observer.ObserverConnectionProtocol;
import observer.ObserverServerProtocol;
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

    private ObserverServerProtocol observer;

    @Override
    public ResponseProtocol handleRequest(RequestProtocol request) {
        InternalRequestHandlerProtocol handler = RequestHandlerFactory.getHandler(request);
        handler.addObserver(observer);
        return handler.solve();
    }

    @Override
    public void setObserver(ObserverServerProtocol observer) {
        this.observer = observer;
    }

}


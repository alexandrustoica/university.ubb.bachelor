package connection;

import exception.ConnectionException;
import observer.ObserverConnectionProtocol;
import response.ResponseProtocol;
import response_handler.*;
/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandler implements ResponseHandlerProtocol {

    private ObserverConnectionProtocol observer;

    @Override
    public void setObserver(ObserverConnectionProtocol observer) {
        this.observer = observer;
    }

    @Override
    public void handleResponse(ResponseProtocol response) {
        InternalResponseHandlerProtocol handler = ResponseHandlerFactory.getHandler(response, observer);
        handler.solve();
    }


}

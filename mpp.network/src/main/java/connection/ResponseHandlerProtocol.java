package connection;

import exception.ConnectionException;
import observer.ObserverConnectionProtocol;
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

public interface ResponseHandlerProtocol {

    void setObserver(ObserverConnectionProtocol observer);
    void handleResponse(ResponseProtocol response);

}

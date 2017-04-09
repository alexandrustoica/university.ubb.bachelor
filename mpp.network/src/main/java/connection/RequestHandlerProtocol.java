package connection;

import observer.ObserverConnectionProtocol;
import observer.ObserverServerProtocol;
import request.RequestProtocol;
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

public interface RequestHandlerProtocol {

    ResponseProtocol handleRequest(RequestProtocol request);
    void setObserver(ObserverServerProtocol observer);

}

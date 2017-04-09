package request_handler;

import observer.ObservableProtocol;
import observer.ObserverServerProtocol;
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

public interface InternalRequestHandlerProtocol
        extends ObservableProtocol<ObserverServerProtocol> {

    ResponseProtocol solve();

}

package connection;

import observer.ObserverConnectionProtocol;
import observer.ObserverResponseProtocol;
import request.RequestProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RequestSenderProtocol extends ObserverResponseProtocol {

    void setObserver(ObserverConnectionProtocol observer);
    void sendRequest(RequestProtocol request);
    void stop();

}

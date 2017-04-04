package network;

import observer.ObserverProtocol;
import observer.ObserverResponseProtocol;
import transferable.RequestProtocol;
import java.io.IOException;

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

    void setObserver(ObserverProtocol observer);
    void sendRequest(RequestProtocol request) throws IOException;

}

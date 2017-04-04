package network;

import observer.ObserverProtocol;
import transferable.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ResponseHandleProtocol {

    void setObserver(ObserverProtocol observer);
    void handleResponse(ResponseProtocol response);

}

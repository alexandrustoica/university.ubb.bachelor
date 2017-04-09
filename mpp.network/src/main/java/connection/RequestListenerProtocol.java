package connection;

import error.Errors;
import observer.ObserverConnectionProtocol;
import observer.ObserverServerProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RequestListenerProtocol extends Runnable {

    void listen();
    void stop();
    void handleErrors(Errors errors);
    void setObserver(ObserverServerProtocol observer);
    
}

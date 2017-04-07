package connection;

import error.Errors;
import exception.ConnectionException;
import observer.ObservableProtocol;
import observer.ObserverProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface ResponseListenerProtocol extends Runnable, ObservableProtocol<ObserverProtocol>{

    void handleErrors(Errors errors);
    void listen();
    void stop();

}

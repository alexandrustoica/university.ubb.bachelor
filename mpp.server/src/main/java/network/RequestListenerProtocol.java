package network;

import error.Errors;

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

}

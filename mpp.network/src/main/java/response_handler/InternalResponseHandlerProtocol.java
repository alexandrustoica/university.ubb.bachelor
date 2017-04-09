package response_handler;

import observer.ObservableProtocol;
import observer.ObserverConnectionProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface InternalResponseHandlerProtocol
        extends ObservableProtocol<ObserverConnectionProtocol> {

    void solve();

}

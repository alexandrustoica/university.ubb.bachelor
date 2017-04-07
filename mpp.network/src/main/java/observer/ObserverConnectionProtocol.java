package observer;

import error.Errors;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        07/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ObserverConnectionProtocol extends ObserverProtocol {

    void notify(Object object);
    void notifyErrors(Errors errors);

}

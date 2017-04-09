package observer;

import domain.User;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        09/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ObserverServerProtocol extends ObserverProtocol {

    void notifyLoggedUser(User user);

}

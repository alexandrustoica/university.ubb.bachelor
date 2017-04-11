package observer;

import domain.User;
import response.ResponseNotification;

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
    void notifyAllUsers(ResponseNotification notification);

}

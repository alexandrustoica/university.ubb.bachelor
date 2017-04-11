package observer;

import response.NotificationType;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ObserverClientProtocol {

    void notify(NotificationType notification);

}

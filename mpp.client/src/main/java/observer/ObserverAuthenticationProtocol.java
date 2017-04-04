package observer;

import domain.User;
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

public interface ObserverAuthenticationProtocol extends ObserverProtocol {

    void notifyErrors(Errors errors);
    void notifySuccess(User user);

}

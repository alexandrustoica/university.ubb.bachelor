package client;

import domain.User;
import error.Errors;
import exception.ConnectionException;
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

public interface ClientTransmissionProtocol extends ObserverConnectionProtocol {

    User requestSignUp(String username, String password, String confirm);
    Errors getErrors();
    User getActiveUser();
    void setActiveUser(User user);

}

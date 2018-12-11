package system;

import domain.User;

import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface UserProtocol {
    void setUser(User user) throws RemoteException;
    User getUser() throws RemoteException;
}

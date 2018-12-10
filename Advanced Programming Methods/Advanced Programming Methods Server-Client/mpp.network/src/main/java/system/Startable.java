package system;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Startable extends Remote {
    void start() throws RemoteException;
}

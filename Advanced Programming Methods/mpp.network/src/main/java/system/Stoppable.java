package system;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Stoppable extends Remote {
    void stop() throws RemoteException;
}

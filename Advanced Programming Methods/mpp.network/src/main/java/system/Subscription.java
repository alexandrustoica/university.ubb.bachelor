package system;

import notification.Notification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Subscription extends Remote {
    void notifySubscribers(Notification notification) throws RemoteException;
    void subscribe(Subscriber subscriber) throws RemoteException;
    void unroll(Subscriber subscriber) throws RemoteException;
}

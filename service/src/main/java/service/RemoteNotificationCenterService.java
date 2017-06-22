package service;

import transfarable.RemoteNotification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface RemoteNotificationCenterService extends Remote {
    void subscribe(SubscriberService subscriber) throws RemoteException;
    void unroll(SubscriberService subscriber) throws RemoteException;
    void notifyAll(RemoteNotification notification) throws RemoteException;
}

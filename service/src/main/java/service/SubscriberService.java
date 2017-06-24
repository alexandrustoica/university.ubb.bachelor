package service;

import dto.RemoteNotification;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubscriberService extends Remote {
    void update(RemoteNotification notification) throws RemoteException;
}

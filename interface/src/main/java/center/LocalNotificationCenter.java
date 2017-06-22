package center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import service.RemoteNotificationCenterService;
import service.SubscriberService;
import transfarable.RemoteNotification;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Lazy
@Component
public class LocalNotificationCenter extends UnicastRemoteObject implements SubscriberService, Serializable, NotificationCenter {

    private List<SubscriberController> subscribers;

    @Autowired
    private RemoteNotificationCenterService remoteNotificationCenter;

    @Autowired
    public LocalNotificationCenter(RemoteNotificationCenterService remoteNotificationCenter) throws RemoteException {
        super();
        subscribers = new ArrayList<>();
        try {
            remoteNotificationCenter.subscribe(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override public void update(RemoteNotification notification) throws RemoteException {
        notifyAll(notification);
    }

    @Override public void subscribe(SubscriberController subscriber) {
        subscribers.add(subscriber);
    }

    @Override public void unroll(SubscriberController subscriber) {
        subscribers.remove(subscriber);
    }

    @Override public void notifyAll(RemoteNotification notification) {
        subscribers.forEach(subscriber -> subscriber.update(notification));
    }
}

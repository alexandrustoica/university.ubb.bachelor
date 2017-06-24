package manager;

import org.apache.log4j.Logger;
import service.RemoteNotificationCenterService;
import service.SubscriberService;
import dto.RemoteNotification;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RemoteNotificationCenter implements RemoteNotificationCenterService {

    private List<SubscriberService> subscribers;
    private static Logger logger;
    private Consumer<RemoteException> handler;

    public RemoteNotificationCenter() {
        subscribers = new ArrayList<>();
        logger = Logger.getLogger(RemoteNotificationCenter.class);
        handler = (exception) -> logger.error(exception.getMessage());
    }

    @Override public void subscribe(SubscriberService subscriber) throws RemoteException {
        subscribers.add(subscriber);
    }

    @Override public void unroll(SubscriberService subscriber) throws RemoteException {
        subscribers.remove(subscriber);
    }

    @Override public void notifyAll(RemoteNotification notification) throws RemoteException {
        subscribers.forEach(subscriber -> updateSubscriber(subscriber, notification));
    }

    private void updateSubscriber(SubscriberService subscriber, RemoteNotification notification) {
        try {
            subscriber.update(notification);
        } catch (RemoteException exception) {
            handler.accept(exception);
        }
    }
}

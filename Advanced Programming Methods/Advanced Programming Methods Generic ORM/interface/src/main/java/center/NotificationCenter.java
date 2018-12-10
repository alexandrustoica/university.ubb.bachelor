package center;

import dto.RemoteNotification;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface NotificationCenter {
    void subscribe(SubscriberController subscriber);
    void unroll(SubscriberController subscriber);
    void notifyAll(RemoteNotification notification);
}

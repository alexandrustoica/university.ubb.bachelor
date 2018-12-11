package network;

import service.Notification;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface Observer {
    void update(Notification notification);
}

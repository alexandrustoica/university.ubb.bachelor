package dto;

import java.io.Serializable;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RemoteNotification implements Serializable {

    private NotificationType type;

    public RemoteNotification(NotificationType type) {
        this.type = type;
    }

    public NotificationType getType() {
        return type;
    }
}

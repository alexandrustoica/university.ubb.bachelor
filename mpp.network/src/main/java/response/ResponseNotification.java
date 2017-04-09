package response;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseNotification implements ResponseProtocol {

    private NotificationType notification;

    public ResponseNotification(NotificationType notification) {
        this.notification = notification;
    }

    @Override
    public ResponseType getType() {
        return ResponseType.NOTIFICATION;
    }

    public NotificationType getNotification() {
        return notification;
    }

}

package center;

import controller.ControllerInterface;
import dto.RemoteNotification;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface SubscriberController extends ControllerInterface {
    void update(RemoteNotification notification);
}

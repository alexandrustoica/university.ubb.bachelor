package response_handler;

import observer.Observable;
import observer.ObserverConnectionProtocol;
import response.ResponseNotification;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        10/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseHandlerNotification
        extends Observable<ObserverConnectionProtocol>
        implements InternalResponseHandlerProtocol {

    private ResponseNotification notification;

    public ResponseHandlerNotification(ResponseNotification notification) {
        this.notification = notification;
    }

    @Override
    public void solve() {
        System.out.println(notification.getNotification());
    }

}

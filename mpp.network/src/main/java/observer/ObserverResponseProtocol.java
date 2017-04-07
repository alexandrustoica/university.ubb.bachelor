package observer;

import exception.ConnectionException;
import response.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public interface ObserverResponseProtocol extends ObserverProtocol {

    void notifyMe(ResponseProtocol response);

}

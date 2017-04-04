package observer;

import transferable.ResponseProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ObservableProtocol<T extends ObserverProtocol> {

    void addObserver(T observer);
    void removeObserver(T observer);

}
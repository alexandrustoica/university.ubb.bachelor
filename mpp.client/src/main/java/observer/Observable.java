package observer;

import transferable.ResponseProtocol;

import java.util.ArrayList;
import java.util.Observer;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Observable<T extends ObserverProtocol>
        implements ObservableProtocol<T> {

    protected ArrayList<T> observers;

    protected Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(T observer) {
        observers.add(observer);
    }

    public void removeObserver(T observer) {
        observers.remove(observer);
    }

}

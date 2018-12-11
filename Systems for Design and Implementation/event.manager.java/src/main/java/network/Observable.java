package network;

import service.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class Observable {

    private List<Observer> observers;

    protected Observable(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Notification notification) {
        observers.forEach(observer -> observer.update(notification));
    }
}

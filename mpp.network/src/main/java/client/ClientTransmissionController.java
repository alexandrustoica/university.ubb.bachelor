package client;

import domain.Event;
import domain.Player;
import domain.User;
import error.Errors;
import observer.ObserverClientProtocol;
import observer.ObserverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import request.*;
import response.ResponseNotification;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */


@Component
public class ClientTransmissionController implements ClientTransmissionProtocol {

    private ClientConnectionManager connectionManager;
    private Object object;
    private Boolean isReady = false;
    private User activeUser;
    private Errors errors;
    private Lock lock = new ReentrantLock();
    private ObserverClientProtocol observer;

    @Autowired
    public ClientTransmissionController(ClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.connectionManager.setObserver(this);
        this.errors = new Errors();
    }


    @Override
    public User requestSignUp(String username, String password, String confirm) {
        RequestProtocol requestSignUp = new RequestSignUp(username, password, confirm);
        connectionManager.send(requestSignUp);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (User)this.object;
        }
        return null;
    }

    @Override
    public User requestLogin(String username, String password) {
        RequestProtocol requestLogin = new RequestLogin(username, password);
        connectionManager.send(requestLogin);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (User) this.object;
        }
        return null;
    }

    @Override
    public ArrayList<Player> getAllPlayers() {
        RequestProtocol requestReadPlayer = new RequestReadPlayer();
        connectionManager.send(requestReadPlayer);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (ArrayList<Player>) this.object;
        }
        return null;
    }

    @Override
    public ArrayList<Player> getPlayersFromEvent(Integer idEvent) {
        RequestProtocol requestReadPlayer = new RequestReadPlayer(idEvent);
        connectionManager.send(requestReadPlayer);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (ArrayList<Player>) this.object;
        }
        return null;
    }

    @Override
    public ArrayList<Event> getAllEvents() {
        RequestProtocol requestReadEvent = new RequestReadEvent();
        connectionManager.send(requestReadEvent);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (ArrayList<Event>) this.object;
        }
        return null;
    }

    @Override
    public ArrayList<Event> getEventsFromPlayer(Integer idPlayer) {
        RequestProtocol requestReadEvent = new RequestReadEvent(idPlayer);
        connectionManager.send(requestReadEvent);
        while (!isReady) {
            lock.lock();
        }
        isReady = false;
        if (this.errors.getErrors() != null) {
            return (ArrayList<Event>) this.object;
        }
        return null;
    }

    @Override
    public void addPlayer(String name, Integer age, ArrayList<Integer> events) {
        RequestProtocol requestReadEvent = new RequestAddPlayer(name, age, events);
        connectionManager.send(requestReadEvent);
    }

    @Override
    public Errors getErrors() {
        Errors errorsResult = this.errors;
        this.errors = new Errors();
        return errorsResult;
    }

    @Override
    public ObserverType getType() {
        return ObserverType.CLIENT;
    }

    @Override
    public void notify(Object object) {
        this.object = object;
        isReady = true;
    }

    @Override
    public void notifyUpdate(ResponseNotification notification) {
        observer.notify(notification.getNotification());
    }


    @Override
    public void notifyErrors(Errors errors) {
        this.errors = errors;
        isReady = true;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void setObserver(ObserverClientProtocol observer) {
        this.observer = observer;
    }

    @Override
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public void exit() {
        connectionManager.stop();
    }

}

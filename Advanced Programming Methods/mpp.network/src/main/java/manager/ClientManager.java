package manager;

import domain.Event;
import domain.Player;
import domain.User;
import notification.Notification;
import service.ClientService;
import service.ServerService;
import system.Subscriber;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ClientManager extends UnicastRemoteObject implements ClientService {

    private ServerService service;
    private RemoteUser user;
    private List<Subscriber> subscribers;

    private class RemoteUser implements Remote {

        private User user;

        public RemoteUser(User user) throws RemoteException {
            this.user = user;
        }

        public User getUser() throws RemoteException {
            return user;
        }
    }

    /**
     * Service wrapper for client view-controllers.
     */
    public ClientManager() throws RemoteException, MalformedURLException, NotBoundException {
        service = (ServerService) Naming.lookup("rmi://localhost:1099/service");
        subscribers = new ArrayList<>();
    }

    @Override
    public void setUser(User user) throws RemoteException {
        this.user = new RemoteUser(user);
    }

    @Override
    public User getUser() throws RemoteException {
        return user.getUser();
    }

    @Override
    public void start() throws RemoteException {
        service.subscribe(this);
    }

    @Override
    public void stop() throws RemoteException {
        service.unroll(this);
    }

    @Override
    public User login(String username, String password) throws RemoteException {
        return service.login(username, password);
    }

    @Override
    public User signUp(String username, String password, String confirm) throws RemoteException {
        return service.signUp(username, password, confirm);
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
        return service.getPlayers();
    }

    @Override
    public List<Event> getEventsFromPlayer(Player player) throws RemoteException {
        return service.getEventsFromPlayer(player);
    }

    @Override
    public List<Event> getEvents() throws RemoteException {
        return service.getEvents();
    }

    @Override
    public List<Player> getPlayerFromEvent(Event event) throws RemoteException {
        return service.getPlayerFromEvent(event);
    }

    @Override
    public Integer addPlayer(String name, Integer age, List<Event> events) throws RemoteException {
        return service.addPlayer(name, age, events);
    }

    @Override
    public void notifySubscribers(Notification notification) throws RemoteException {
        update(notification);
    }

    @Override
    public void subscribe(Subscriber subscriber) throws RemoteException {
        subscribers.add(subscriber);
    }

    @Override
    public void unroll(Subscriber subscriber) throws RemoteException {
        subscribers.remove(subscriber);
    }

    @Override
    public void update(Notification notification) throws RemoteException {
        subscribers.forEach(subscriber -> {
            try {
                subscriber.update(notification);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}

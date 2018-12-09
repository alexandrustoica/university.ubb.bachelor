package manager;

import domain.Event;
import domain.Player;
import domain.User;
import model.ModelEvent;
import model.ModelPlayer;
import model.ModelUser;
import notification.Notification;
import notification.Update;
import service.ServerService;
import system.Subscriber;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ServerManager implements ServerService {

    private ModelUser modelUser;
    private ModelEvent modelEvent;
    private ModelPlayer modelPlayer;
    private List<Subscriber> subscribers;

    private String getDatabaseURL() {
        return getDataFromBundle("server.database");
    }

    private static String getDataFromBundle(final String key) {
        return ResourceBundle.getBundle("server").getString(key);
    }

    public ServerManager() throws RemoteException {
        super();
        subscribers = new ArrayList<>();
        modelUser = new ModelUser(getDatabaseURL());
        modelPlayer = new ModelPlayer(getDatabaseURL());
        modelEvent = new ModelEvent(getDatabaseURL());
    }

    @Override
    public synchronized User login(String username, String password) throws RemoteException {
        return modelUser.getAll().stream().filter(user ->
                user.getName().equals(username) && user.getPassword().equals(password)).findFirst().get();
    }

    private synchronized Boolean validate(String username, String password, String confirm) {
        return modelUser.getAll().stream().noneMatch(user ->
                user.getName().equals(username)) && password.equals(confirm);
    }

    @Override
    public synchronized User signUp(String username, String password, String confirm) throws RemoteException {
        if (validate(username, password, confirm)) {
            return modelUser.getElementById(modelUser.add(new User(username, password)));
        }
        throw new RemoteException("Invalid Username or Password!");
    }

    @Override
    public synchronized List<Player> getPlayers() throws RemoteException {
        return modelPlayer.getAll();
    }

    @Override
    public synchronized List<Event> getEventsFromPlayer(Player player) throws RemoteException {
        return modelPlayer.getEventsFromPlayer(player.getID());
    }

    @Override
    public synchronized List<Event> getEvents() throws RemoteException {
        return modelEvent.getAll();
    }

    @Override
    public synchronized List<Player> getPlayerFromEvent(Event event) throws RemoteException {
        return modelEvent.getPlayersFromEvent(event.getID());
    }

    @Override
    public void notifySubscribers(Notification notification) throws RemoteException {
        subscribers.forEach(subscriber -> {
            try { subscriber.update(notification); }
            catch (RemoteException exception){ exception.printStackTrace(); }});
    }

    @Override
    public synchronized Integer addPlayer(String name, Integer age, List<Event> events) throws RemoteException {
        Integer id = modelPlayer.add(new Player(name, age));
        events.forEach(event -> modelEvent.addPlayerToEvent(id, event.getID()));
        notifySubscribers(Update.ALL);
        return id;
    }

    private void update() {
        System.out.println(System.lineSeparator() + "Update" + System.lineSeparator());
        subscribers.forEach(subscriber ->
                System.out.print("#" + subscribers.indexOf(subscriber) + " Client " + System.lineSeparator()));
    }

    @Override
    public synchronized void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber); update();
    }

    @Override
    public synchronized void unroll(Subscriber subscriber) {
        subscribers.remove(subscriber); update();
    }

}

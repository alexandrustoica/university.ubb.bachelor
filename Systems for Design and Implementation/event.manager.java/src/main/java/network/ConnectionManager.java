package network;

import domain.Event;
import domain.EventStyle;
import domain.Player;
import domain.User;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import service.*;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectionManager extends Observable implements Connection {

    private final String host;
    private final Integer serverPort;
    private Integer clientPort;
    private Subsciption.Client connection;
    private User active;

    public ConnectionManager(String host, Integer port) throws TTransportException {
        this.host = host;
        this.serverPort = port;
        clientPort = new Random().nextInt(9999) + 1000;
        Runnable task = () -> {
            Listener listener = new Listener(clientPort);
            listener.addObserver(this);
            listener.run();
        };
        new Thread(task).start();
    }

    @Override
    public void start() throws TException {
        TTransport transport = new TSocket(host, serverPort);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        connection = new Subsciption.Client(protocol);
        connection.subscribe(host, clientPort);
    }

    @Override
    public void setUser(User user) {
        this.active = user;
    }

    @Override
    public User getUser() {
        return active;
    }

    @Override
    public void popObserver(Observer observer) {
        removeObserver(observer);
    }

    @Override
    public void pushObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Notification notification) {
        notifyObservers(notification);
    }

    private UserData convertToUserData(User user) {
        return new UserData(user.getName(), user.getPassword());
    }

    private User convertToUser(UserData user) {
        return new User(user.id, user.username, user.password);
    }

    private PlayerData convertToPlayerData(Player player) {
        return new PlayerData(player.getName(), player.getAge());
    }

    private Player convertToPlayer(PlayerData player) {
        return new Player(player.id, player.name, player.age);
    }

    private EventData convertToEventData(Event event) {
        return new EventData(event.getDistance(), event.getStyle().toString());
    }

    private Event convertToEvent(EventData event) {
        return new Event(event.id, event.distance, EventStyle.fromString(event.style));
    }

    @Override
    public User login(String username, String password) throws TException {
        return convertToUser(connection.login(username, password));
    }

    @Override
    public User signUp(String username, String password, String confirm) throws TException {
        return convertToUser(connection.signUp(username, password, confirm));
    }

    @Override
    public List<Player> getPlayers() throws TException {
        return connection.getPlayers().stream().map(this::convertToPlayer).collect(Collectors.toList());
    }

    @Override
    public List<Event> getEventsFromPlayer(Player player) throws TException {
        return connection.getEventsFromPlayer(convertToPlayerData(player))
                .stream().map(this::convertToEvent).collect(Collectors.toList());
}

    @Override
    public List<Event> getEvents() throws TException {
        return connection.getEvents().stream().map(this::convertToEvent).collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayerFromEvent(Event event) throws TException {
        return connection.getPlayersFromEvent(convertToEventData(event))
                .stream().map(this::convertToPlayer).collect(Collectors.toList());
    }

    @Override
    public Integer addPlayer(String name, Integer age, List<Event> events) throws TException {
        return connection.addPlayer(convertToPlayerData(new Player(name, age)),
                events.stream().map(this::convertToEventData).collect(Collectors.toList()));
    }

    @Override
    public void stop() throws TException {
        connection.unsubscribe(host, clientPort);
    }
}

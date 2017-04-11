package server;

import domain.User;
import error.Errors;
import observer.ObserverType;
import response.NotificationType;
import response.ResponseNotification;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ServerTransmissionController implements ServerTransmissionProtocol {

    private ServerConnectionManager connectionManager;
    private ArrayList<Client> clients;
    private Integer orderNumber = 1;

    public ServerTransmissionController(ServerConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.connectionManager.setObserver(this);
        clients = new ArrayList<>();
    }

    @Override
    public void start() {
        connectionManager.start();
    }

    @Override
    public ObserverType getType() {
        return ObserverType.SERVER;
    }

    @Override
    public void notifyLoggedUser(User user) {
        Client client = new Client(orderNumber, user);
        orderNumber += 1;
        clients.add(client);
        connectionManager.notifyClients(NotificationType.UPDATE);
    }

    @Override
    public void notifyAllUsers(ResponseNotification notification) {
        connectionManager.notifyClients(notification.getNotification());
    }

}

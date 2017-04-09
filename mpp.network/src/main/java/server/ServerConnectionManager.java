package server;

import connection.RequestListener;
import connection.RequestListenerProtocol;
import observer.ObserverServerProtocol;
import response.NotificationType;
import response.ResponseNotification;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        02/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ServerConnectionManager implements ConcurrentServerProtocol {

    private ServerSocket serverSocket;
    private Integer port;
    private ObserverServerProtocol observer;
    private ArrayList<RequestListenerProtocol> clients;

    public ServerConnectionManager(Integer port) {
        this.port = port;
        this.clients = new ArrayList<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                processClient(socket);
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void setObserver(ObserverServerProtocol observer) {
        this.observer = observer;
    }

    public void processClient(Socket socket) {
        Thread thread = createThread(socket);
        thread.start();
    }

    public void notifyClients(NotificationType notification) {
        clients.forEach(client ->
                client.sendNotification(new ResponseNotification(notification)));
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException error) {
            System.out.print(error.getMessage());
        }
    }

    public Thread createThread(Socket socket) {
        RequestListenerProtocol listener = new RequestListener(socket);
        listener.setObserver(observer);
        clients.add(listener);
        return new Thread(listener);
    }

}

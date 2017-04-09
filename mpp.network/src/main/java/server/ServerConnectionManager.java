package server;

import connection.RequestListener;
import connection.RequestListenerProtocol;
import observer.ObserverConnectionProtocol;
import observer.ObserverServerProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

    public ServerConnectionManager(Integer port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                processRequest(socket);
            }
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void setObserver(ObserverServerProtocol observer) {
        this.observer = observer;
    }

    public void processRequest(Socket socket) {
        Thread thread = createThread(socket);
        thread.start();
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
        return new Thread(listener);
    }

}

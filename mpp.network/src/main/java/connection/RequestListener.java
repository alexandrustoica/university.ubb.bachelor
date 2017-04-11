package connection;

import error.Errors;
import error.Error;
import observer.ObserverServerProtocol;
import request.RequestProtocol;
import response.ResponseNotification;
import response.ResponseProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestListener implements RequestListenerProtocol {

    private final Socket socket;
    private ObjectInputStream input;

    private Boolean connected;

    private RequestHandlerProtocol handler;
    private ResponseSenderProtocol sender;

    public RequestListener(Socket socket) {
        Errors errors = new Errors();
        handler = new RequestHandler();
        sender = new ResponseSender(socket);
        this.socket = socket;
        try {
            input = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (IOException error) {
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
            connected = false;
        }
    }

    @Override
    public synchronized void listen() {
        Errors errors = new Errors();
        try {
            RequestProtocol request = (RequestProtocol) input.readObject();
            ResponseProtocol response = handler.handleRequest(request);
            sender.sendResponse(response);
        } catch (IOException | ClassNotFoundException error) {
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
            connected = false;
        }
    }

    @Override
    public void run() {
        while(connected) {
            listen();
        }
        stop();
    }

    @Override
    public void stop() {
        Errors errors = new Errors();
        try {
            input.close();
            socket.close();
        } catch (IOException error) {
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
        }
    }

    @Override
    public void sendNotification(ResponseNotification notification) {
        Errors errors = new Errors();
        try {
            sender.sendResponse(notification);
        } catch (IOException error) {
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
            connected = false;
        }
    }

    @Override
    public void handleErrors(Errors errors) {
        System.out.println(errors.getMessage());
    }

    @Override
    public void setObserver(ObserverServerProtocol observer) {
        handler.setObserver(observer);
    }

}

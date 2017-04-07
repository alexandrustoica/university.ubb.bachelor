package client;

import connection.RequestSender;
import connection.RequestSenderProtocol;
import error.Error;
import error.Errors;
import observer.ObserverConnectionProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import request.RequestProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

@Component
public class ClientConnectionManager {

    private RequestSenderProtocol sender;

    @Autowired
    public ClientConnectionManager(String host, Integer port) {
        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            this.sender = new RequestSender(input, output);
        } catch (IOException error) {
            Errors errors = new Errors();
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
        }
    }

    public void setObserver(ObserverConnectionProtocol observer) {
        this.sender.setObserver(observer);
    }

    void send(RequestProtocol request) {
        sender.sendRequest(request);
    }

    private void handleErrors(Errors errors) {
        System.out.println(errors.getMessage());
    }

}

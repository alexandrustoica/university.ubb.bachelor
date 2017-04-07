package connection;

import error.Error;
import error.Errors;
import exception.ConnectionException;
import observer.ObserverConnectionProtocol;
import observer.ObserverType;
import request.RequestProtocol;
import response.ResponseProtocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RequestSender implements RequestSenderProtocol {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ResponseHandlerProtocol handler;

    public RequestSender(ObjectInputStream input, ObjectOutputStream output) {
        this.input = input;
        this.output = output;
        this.handler = new ResponseHandler();
    }

    @Override
    public void setObserver(ObserverConnectionProtocol observer) {
        handler.setObserver(observer);
    }

    @Override
    public synchronized void sendRequest(RequestProtocol request) {
        Errors errors = new Errors();
        try {
           output.writeObject(request);
           output.flush();
           startResponseListener();
        } catch (IOException error) {
           errors.add(new Error(error.getMessage()));
           handleErrors(errors);
        }
    }

    private void startResponseListener() {
        ResponseListenerProtocol listener = new ResponseListener(input);
        listener.addObserver(this);
        Thread thread = new Thread(listener);
        thread.start();
    }

    private void handleErrors(Errors errors) {
        System.out.println(errors.getMessage());
    }

    @Override
    public void notifyMe(ResponseProtocol response) {
        handler.handleResponse(response);
    }

    @Override
    public ObserverType getType() {
        return ObserverType.RESPONSE;
    }

}

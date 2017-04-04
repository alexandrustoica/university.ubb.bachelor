package network;

import error.Errors;
import observer.ObserverProtocol;
import observer.ObserverType;
import transferable.RequestProtocol;
import transferable.ResponseProtocol;

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
    private ResponseHandleProtocol handler;

    public RequestSender(ObjectInputStream input, ObjectOutputStream output) {
        this.input = input;
        this.output = output;
        this.handler = new ResponseHandler();
    }

    @Override
    public void setObserver(ObserverProtocol observer) {
        handler.setObserver(observer);
    }

    @Override
    public synchronized void sendRequest(RequestProtocol request) throws IOException {
        output.writeObject(request);
        output.flush();
        startResponseListener();
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

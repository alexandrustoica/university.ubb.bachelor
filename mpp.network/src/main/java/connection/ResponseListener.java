package connection;

import error.Errors;
import error.Error;
import exception.ConnectionException;
import observer.Observable;
import observer.ObserverProtocol;
import observer.ObserverResponseProtocol;
import observer.ObserverType;
import response.ResponseProtocol;
import response.ResponseType;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ResponseListener extends Observable<ObserverProtocol> implements ResponseListenerProtocol {

    private ObjectInputStream input;
    private Boolean isFinished;

    public ResponseListener(ObjectInputStream input) {
        this.input = input;
        isFinished = false;
    }

    @Override
    public void handleErrors(Errors errors) {
        System.out.println(errors.getMessage());
    }

    @Override
    public void listen() {
        Errors errors = new Errors();
        try {
            ResponseProtocol response = (ResponseProtocol) input.readObject();
            if (response != null) {
                for(ObserverProtocol observer : observers) {
                    if (observer.getType().equals(ObserverType.RESPONSE)) {
                        ((ObserverResponseProtocol) observer).notifyMe(response);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException error) {
            errors.add(new Error(error.getMessage()));
            handleErrors(errors);
        }
    }

    @Override
    public void stop() {
        isFinished = true;
    }

    @Override
    public void run() {
        while(!isFinished) {
            listen();
        }
        stop();
    }

}

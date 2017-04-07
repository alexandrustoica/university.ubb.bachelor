package client;

import domain.User;
import error.Errors;
import observer.ObserverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import request.RequestLogin;
import request.RequestProtocol;
import request.RequestSignUp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author      Alexandru Stoica
 * @version     1.0
 */


@Component
public class ClientTransmissionController implements ClientTransmissionProtocol {


    private ClientConnectionManager connectionManager;
    private Object object;
    private Boolean isReady = false;
    private User activeUser;
    private Errors errors;
    private Lock lock = new ReentrantLock();

    @Autowired
    public ClientTransmissionController(ClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.connectionManager.setObserver(this);
        this.errors = new Errors();
    }


    @Override
    public User requestSignUp(String username, String password, String confirm) {
        RequestProtocol requestSignUp = new RequestSignUp(username, password, confirm);
        connectionManager.send(requestSignUp);
        while (!isReady) {
            lock.lock();
        }
        if (this.errors.getErrors().isEmpty()) {
            return (User)this.object;
        }
        return null;
    }

    @Override
    public User requestLogin(String username, String password) {
        RequestProtocol requestLogin = new RequestLogin(username, password);
        connectionManager.send(requestLogin);
        while (!isReady) {
            lock.lock();
        }
        if (this.errors.getErrors().isEmpty()) {
            return (User)this.object;
        }
        return null;
    }

    @Override
    public Errors getErrors() {
        return errors;
    }

    @Override
    public ObserverType getType() {
        return ObserverType.CLIENT;
    }

    @Override
    public void notify(Object object) {
        this.object = object;
        isReady = true;
    }

    @Override
    public void notifyErrors(Errors errors) {
        this.errors = errors;
        isReady = true;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

}

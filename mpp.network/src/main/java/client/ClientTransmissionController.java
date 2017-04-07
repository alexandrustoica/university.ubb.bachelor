package client;

import domain.User;
import exception.ConnectionException;
import observer.ObserverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    @Autowired
    public ClientTransmissionController(ClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.connectionManager.setObserver(this);
    }

    @Override
    public User setActiveUser(String username, String password) {
        // RequestProtocol requestProtocol = new LoginRequest(username, password);
        // connectionManager.send(requestProtocol);
        // TODO
        return null;
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
}

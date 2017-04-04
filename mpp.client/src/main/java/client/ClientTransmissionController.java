package client;

import observer.ObserverProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transferable.*;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


@Component
public class ClientTransmissionController implements ClientTransmissionProtocol {

    private ClientConnectionManager connectionManager;

    @Autowired
    public ClientTransmissionController(ClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }


    @Override
    public void sendLoginRequest(String username, String password) {
        RequestProtocol request = new LoginRequest(username, password);
        connectionManager.send(request);
    }

    @Override
    public void sendSignUpRequest(String username, String password, String confirm) {
        RequestProtocol request = new SignUpRequest(username, password, confirm);
        connectionManager.send(request);
    }

    @Override
    public void sendGetPlayersOfEventRequest(Integer idEvent) {
        RequestProtocol request = new GetPlayerRequest(idEvent);
        connectionManager.send(request);
    }

    @Override
    public void sendGetPlayersRequest() {
        RequestProtocol request = new GetPlayerRequest(0);
        connectionManager.send(request);
    }

    @Override
    public void sendGetEventsOfPlayerRequest(Integer idPlayer) {
        RequestProtocol request = new GetEventRequest(idPlayer);
        connectionManager.send(request);
    }

    @Override
    public void sendGetEventsRequest() {
        RequestProtocol request = new GetEventRequest(0);
        connectionManager.send(request);
    }

    public void setObserver(ObserverProtocol observer) {
        connectionManager.setObserver(observer);
    }

}

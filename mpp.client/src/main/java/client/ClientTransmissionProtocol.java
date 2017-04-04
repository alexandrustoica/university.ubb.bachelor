package client;

import observer.ObserverProtocol;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ClientTransmissionProtocol {

    void sendLoginRequest(String username, String password);
    void sendSignUpRequest(String username, String password, String confirm);

    void sendGetPlayersOfEventRequest(Integer idEvent);
    void sendGetPlayersRequest();

    void sendGetEventsOfPlayerRequest(Integer idPlayer);
    void sendGetEventsRequest();

    //void sendMessageRequest();
    void setObserver(ObserverProtocol observer);

}

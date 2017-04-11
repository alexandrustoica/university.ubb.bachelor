package client;

import domain.Event;
import domain.Player;
import domain.User;
import error.Errors;
import observer.ObserverClientProtocol;
import observer.ObserverConnectionProtocol;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        03/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ClientTransmissionProtocol extends ObserverConnectionProtocol {

    User requestSignUp(String username, String password, String confirm);
    User requestLogin(String username, String password);

    ArrayList<Player> getAllPlayers();
    ArrayList<Player> getPlayersFromEvent(Integer idEvent);

    ArrayList<Event> getAllEvents();
    ArrayList<Event> getEventsFromPlayer(Integer idPlayer);

    void addPlayer(String name, Integer age, ArrayList<Integer> events);

    Errors getErrors();
    User getActiveUser();

    void setObserver(ObserverClientProtocol observer);
    void setActiveUser(User user);

    void exit();
}

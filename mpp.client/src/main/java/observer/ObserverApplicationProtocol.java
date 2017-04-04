package observer;

import domain.Event;
import domain.Player;

import java.util.ArrayList;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        04/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ObserverApplicationProtocol extends ObserverProtocol {

    void notifyGetPlayers(ArrayList<Player> players);
    void notifyGetEvents(ArrayList<Event> events);
}


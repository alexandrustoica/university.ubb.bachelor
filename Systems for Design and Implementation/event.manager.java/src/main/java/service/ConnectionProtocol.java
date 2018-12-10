package service;

import domain.Event;
import domain.Player;
import domain.User;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */
public interface ConnectionProtocol {

    /**
     * Enable user access to data based on username [unique] and password.
     * @param username the user's name [unique]
     * @param password the user's password
     * @return the logged user in our system
     */
    User login(String username, String password) throws TException;

    /**
     * Creates a new user and allows him to access system data.
     * @param username the user's name [unique]
     * @param password the user's password
     * @param confirm the password's confirmation
     * @return the new user logged in our system
     */
    User signUp(String username, String password, String confirm) throws TException;

    /**
     * Returns a list of all the players from our system.
     */
    List<Player> getPlayers() throws TException;

    /**
     * Returns a list of all the events for a specified 'player'.
     * @param player the specified 'player'
     */
    List<Event> getEventsFromPlayer(Player player) throws TException;

    /**
     * Returns a list of all the events from our system.
     */
    List<Event> getEvents() throws TException;

    /**
     * Returns a list of all the players for a specified 'event'.
     * @param event the specified 'event'
     */
    List<Player> getPlayerFromEvent(Event event) throws TException;

    /**
     * Adds an player to system and returns it's id value.
     * @param name the player's name
     * @param age the player's age
     * @param events the list of events pursued by new 'player'
     */
    Integer addPlayer(String name, Integer age, List<Event> events) throws TException;

}

package model;

import domain.Event;
import domain.EventStyle;
import domain.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class ModelPlayerTest {

    private ModelPlayer model;
    private final String testDatabase = "test.db";

    @Before
    public void setUp() throws Exception {
        this.model = new ModelPlayer(testDatabase);
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test.db");
        final boolean delete = file.delete();
        assertTrue(delete);
    }

    @Test
    public void add() throws Exception {
        Player player = new Player(1, "Test", 10);
        model.add(player);
        assertTrue(model.getAll().get(0).isEqual(player) &&
                model.getAll().size() == 1);
    }

    @Test
    public void update() throws Exception {
        Player first = new Player(1, "Test", 10);
        Player update = new Player(1, "Update", 100);
        model.add(first);
        model.update(first, update);
        assertTrue(model.getAll().get(0).isEqual(update) &&
                model.getAll().size() == 1);
    }

    @Test
    public void delete() throws Exception {
        Player first = new Player(1, "Test", 10);
        Player second = new Player(2, "Update", 100);
        model.add(first);
        model.add(second);
        model.delete(first);
        assertTrue(model.getAll().get(0).isEqual(second) &&
                model.getAll().size() == 1);
    }

    @Test
    public void getAll() throws Exception {
        Player first = new Player(1, "Test", 10);
        Player second = new Player(2, "Update", 100);
        model.add(first);
        model.add(second);
        assertTrue(model.getAll().get(0).isEqual(first) &&
                model.getAll().get(1).isEqual(second) &&
                model.getAll().size() == 2);
    }

    @Test
    public void getElementById() throws Exception {
        Player player = new Player(1, "Test", 10);
        model.add(player);
        assertTrue(model.getElementById(1).isEqual(player));
    }

    @Test
    public void isAddingEventToPlayer() throws Exception {
        Player player = new Player(1, "Player", 10);
        Event event = new Event(1, 200, EventStyle.MIX);
        model.add(player);
        ModelEvent modelEvent = new ModelEvent(testDatabase);
        modelEvent.add(event);
        model.addEventToPlayer(event.getID(), player.getID());
        assertTrue(model.getEventsFromPlayer(player.getID()).get(0).isEqual(event));
    }

    @Test
    public void isDeletingEventFromPlayer() throws Exception {
        Player player = new Player(1, "Player", 10);
        Event event = new Event(1, 200, EventStyle.MIX);
        model.add(player);
        ModelEvent modelEvent = new ModelEvent(testDatabase);
        modelEvent.add(event);
        model.addEventToPlayer(event.getID(), player.getID());
        /// @note: Making sure that we have a relationship in database.
        assertTrue(model.getEventsFromPlayer(player.getID()).get(0).isEqual(event));
        model.deleteEventFromPlayer(event.getID(), player.getID());
        assertTrue(model.getEventsFromPlayer(player.getID()).isEmpty());
    }

    @Test
    public void isGettingEventsFromPlayer() throws Exception {
        Player firstPlayer = new Player(1, "Player", 10);
        Event firstEvent = new Event(1, 200, EventStyle.MIX);
        Player secondPlayer = new Player(2, "Player", 10);
        Event secondEvent = new Event(2, 200, EventStyle.MIX);

        model.add(firstPlayer);
        model.add(secondPlayer);

        ModelEvent modelEvent = new ModelEvent(testDatabase);
        modelEvent.add(firstEvent);
        modelEvent.add(secondEvent);

        model.addEventToPlayer(firstEvent.getID(), firstPlayer.getID());
        model.addEventToPlayer(secondEvent.getID(), firstPlayer.getID());
        model.addEventToPlayer(firstEvent.getID(), secondPlayer.getID());
        model.addEventToPlayer(secondEvent.getID(), secondPlayer.getID());

        assertTrue(model.getEventsFromPlayer(firstPlayer.getID()).size() == 2 &&
                model.getEventsFromPlayer(firstPlayer.getID()).get(0).isEqual(firstEvent) &&
                model.getEventsFromPlayer(firstPlayer.getID()).get(1).isEqual(secondEvent) &&
                model.getEventsFromPlayer(secondPlayer.getID()).size() == 2 &&
                model.getEventsFromPlayer(secondPlayer.getID()).get(0).isEqual(firstEvent) &&
                model.getEventsFromPlayer(secondPlayer.getID()).get(1).isEqual(secondEvent));
    }

}
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

public class ModelEventTest {

    private ModelEvent model;
    private final String testDatabase = "test.db";

    @Before
    public void setUp() throws Exception {
        this.model = new ModelEvent(testDatabase);
    }

    @Test
    public void add() throws Exception {
        Event event = new Event(1,100, EventStyle.BACK);
        this.model.add(event);
        assertTrue(this.model.getAll().get(0).isEqual(event));
    }

    @Test
    public void update() throws Exception {
        Event first = new Event(1,100, EventStyle.BACK);
        Event second = new Event(1, 124, EventStyle.BACK);
        this.model.add(first);
        this.model.update(first, second);
        assertTrue(this.model.getAll().get(0).isEqual(second) &&
                this.model.getAll().size() == 1);
    }

    @Test
    public void delete() throws Exception {
        Event first = new Event(1,100, EventStyle.BACK);
        Event second = new Event(2, 124, EventStyle.BACK);
        this.model.add(first);
        this.model.add(second);
        this.model.delete(first);
        assertTrue(this.model.getAll().get(0).isEqual(second) &&
                this.model.getAll().size() == 1);
    }

    @Test
    public void getAll() throws Exception {
        Event first = new Event(1,100, EventStyle.BACK);
        Event second = new Event(2, 425, EventStyle.BUTTERFLY);
        this.model.add(first);
        this.model.add(second);
        assertTrue(this.model.getAll().get(0).isEqual(first) &&
                this.model.getAll().get(1).isEqual(second) &&
                this.model.getAll().size() == 2);
    }

    @Test
    public void getElementById() throws Exception {
        Event event = new Event(1,100, EventStyle.BACK);
        this.model.add(event);
        assertTrue(this.model.getElementById(1).isEqual(event));
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test.db");
        final boolean delete = file.delete();
        assertTrue(delete);
    }

    @Test
    public void isAddingPlayerToEvent() throws Exception {
        Player player = new Player(1, "Player", 10);
        Event event = new Event(1, 200, EventStyle.MIX);
        model.add(event);
        ModelPlayer modelPlayer = new ModelPlayer(testDatabase);
        modelPlayer.add(player);
        model.addPlayerToEvent(player.getID(), event.getID());
        assertTrue(model.getPlayersFromEvent(event.getID()).get(0).isEqual(player));
    }

    @Test
    public void isDeletingEventFromPlayer() throws Exception {
        Player player = new Player(1, "Player", 10);
        Event event = new Event(1, 200, EventStyle.MIX);
        model.add(event);
        ModelPlayer modelPlayer = new ModelPlayer(testDatabase);
        modelPlayer.add(player);
        model.addPlayerToEvent(player.getID(), event.getID());
        /// @note:  Making sure that we have a relationship in database.
        assertTrue(model.getPlayersFromEvent(event.getID()).get(0).isEqual(player));
        model.deletePlayerFromEvent(player.getID(), event.getID());
        assertTrue(model.getPlayersFromEvent(event.getID()).isEmpty());
    }

    @Test
    public void isGettingEventsFromPlayer() throws Exception {
        Player firstPlayer = new Player(1, "Player", 10);
        Event firstEvent = new Event(1, 200, EventStyle.MIX);
        Player secondPlayer = new Player(2, "Player", 10);
        Event secondEvent = new Event(2, 200, EventStyle.MIX);

        model.add(firstEvent);
        model.add(secondEvent);

        ModelPlayer modelPlayer = new ModelPlayer(testDatabase);
        modelPlayer.add(firstPlayer);
        modelPlayer.add(secondPlayer);

        model.addPlayerToEvent(firstPlayer.getID(), firstEvent.getID());
        model.addPlayerToEvent(secondEvent.getID(), firstEvent.getID());
        model.addPlayerToEvent(firstPlayer.getID(), secondEvent.getID());
        model.addPlayerToEvent(secondEvent.getID(), secondEvent.getID());

        assertTrue(model.getPlayersFromEvent(firstEvent.getID()).size() == 2 &&
                model.getPlayersFromEvent(firstEvent.getID()).get(0).isEqual(firstPlayer) &&
                model.getPlayersFromEvent(firstEvent.getID()).get(1).isEqual(secondPlayer) &&
                model.getPlayersFromEvent(secondEvent.getID()).size() == 2 &&
                model.getPlayersFromEvent(secondEvent.getID()).get(0).isEqual(firstPlayer) &&
                model.getPlayersFromEvent(secondEvent.getID()).get(1).isEqual(secondPlayer));
    }

}
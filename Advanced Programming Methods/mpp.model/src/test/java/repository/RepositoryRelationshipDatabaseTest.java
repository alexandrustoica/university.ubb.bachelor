package repository;

import configuration.*;
import database.ColumnType;
import database.DatabaseTable;
import database.DatabaseTableManager;
import domain.Event;
import domain.EventStyle;
import domain.Player;
import error.Errors;
import org.junit.After;
import org.junit.Assert;
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

public class RepositoryRelationshipDatabaseTest {

    private RepositoryRelationshipDatabase<Player, Event, Integer, Integer> test;
    private RepositoryEntityDatabase<Player, Integer> players;
    private RepositoryEntityDatabase<Event, Integer> events;

    @Before
    public void setUp() throws Exception {
        String database = "test.db";

        DatabaseTable databasePlayer = new DatabaseTable("Player");
        databasePlayer.addColumn("ID", ColumnType.ID);
        databasePlayer.addColumn("NAME", ColumnType.STRING);
        databasePlayer.addColumn("AGE", ColumnType.INT);
        DatabaseTableManager tableManagerPlayer = new DatabaseTableManager(database, databasePlayer);

        DatabaseTable databaseEvent = new DatabaseTable("Event");
        databaseEvent.addColumn("ID", ColumnType.ID);
        databaseEvent.addColumn("DISTANCE", ColumnType.INT);
        databaseEvent.addColumn("STYLE", ColumnType.STRING);

        DatabaseTableManager tableManagerEvent = new DatabaseTableManager(database, databaseEvent);
        DatabaseTable databaseTable = new DatabaseTable("PlayerEvent");
        databaseTable.addColumn("ID_PLAYER", ColumnType.INT);
        databaseTable.addColumn("ID_EVENT", ColumnType.INT);

        DatabaseTableManager tableManager = new DatabaseTableManager(database, databaseTable);

        try {
            tableManagerPlayer.makeTable();
            tableManagerEvent.makeTable();
            tableManager.makeTable();

            ConfigurationProtocol<Player, Integer> configurationPlayer = ConfigurationFactory.build(ConfigurationType.PLAYER);
            this.players = new RepositoryEntityDatabase<>(database, configurationPlayer);

            ConfigurationProtocol<Event, Integer> configurationEvent = ConfigurationFactory.build(ConfigurationType.EVENT);
            this.events = new RepositoryEntityDatabase<>(database, configurationEvent);

            ConfigurationRelationProtocol<Player, Event, Integer, Integer> configuration = new PlayerEventConfiguration();
            this.test = new RepositoryRelationshipDatabase<>(database, configuration);
        } catch (Errors errors) {
            System.out.print(errors.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test.db");
        file.delete();
    }

    @Test
    public void add() throws Exception {
        try {
            Player playerA = new Player(1, "Test1", 10);
            Player playerB = new Player(2, "Test2", 10);
            Event eventA = new Event(1, 10, EventStyle.BACK);
            Event eventB = new Event(2, 100, EventStyle.MIX);
            players.add(playerA);
            players.add(playerB);
            events.add(eventA);
            events.add(eventB);
            test.add(playerA, eventA);
            test.add(playerB, eventA);
            test.add(playerA, eventB);
            test.add(playerB, eventB);
            Assert.assertTrue(test.getTObjectsByID(eventA.getID()).get(0).isEqual(playerA));
            Assert.assertTrue(test.getTObjectsByID(eventA.getID()).get(1).isEqual(playerB));
            Assert.assertTrue(test.getEObjectsByID(playerA.getID()).get(0).isEqual(eventA));
            Assert.assertTrue(test.getEObjectsByID(playerA.getID()).get(1).isEqual(eventB));
            Assert.assertTrue(test.getTObjectsByID(eventA.getID()).size() == 2);
            Assert.assertTrue(test.getEObjectsByID(playerA.getID()).size() == 2);
        } catch (Errors errors) {
            System.out.println(errors);
        }
    }

    @Test
    public void delete() throws Exception {
        try {
            Player playerA = new Player(1, "Test1", 10);
            Player playerB = new Player(2, "Test2", 10);
            Event event = new Event(1, 10, EventStyle.BACK);
            players.add(playerA);
            players.add(playerB);
            events.add(event);
            test.add(playerA, event);
            test.add(playerB, event);
            Assert.assertTrue(test.getTObjectsByID(event.getID()).get(0).isEqual(playerA));
            Assert.assertTrue(test.getTObjectsByID(event.getID()).get(1).isEqual(playerB));
            Assert.assertTrue(test.getTObjectsByID(event.getID()).size() == 2);
            test.delete(playerA, event);
            Assert.assertTrue(test.getTObjectsByID(event.getID()).get(0).isEqual(playerB));
            Assert.assertTrue(test.getTObjectsByID(event.getID()).size() == 1);
        } catch (Errors errors) {
            System.out.println(errors);
        }
    }

    @Test
    public void getTObjectsByID() throws Exception {
        try {
            Player playerA = new Player(1, "Test1", 10);
            Player playerB = new Player(2, "Test2", 10);
            Event event = new Event(1, 10, EventStyle.BACK);
            players.add(playerA);
            players.add(playerB);
            events.add(event);
            test.add(playerA, event);
            test.add(playerB, event);
            Assert.assertTrue(test.getTObjectsByID(event.getID()).get(0).isEqual(playerA));
            Assert.assertTrue(test.getTObjectsByID(event.getID()).get(1).isEqual(playerB));
            Assert.assertTrue(test.getTObjectsByID(event.getID()).size() == 2);
        } catch (Errors errors) {
            System.out.println(errors);
        }
    }

    @Test
    public void getEObjectsByID() throws Exception {
        try {
            Player player = new Player(1, "Test", 10);
            Event eventA = new Event(1, 10, EventStyle.BACK);
            Event eventB = new Event(2, 20, EventStyle.MIX);
            players.add(player);
            events.add(eventA);
            events.add(eventB);
            test.add(player, eventA);
            test.add(player, eventB);
            Assert.assertTrue(test.getEObjectsByID(player.getID()).get(0).isEqual(eventA));
            Assert.assertTrue(test.getEObjectsByID(player.getID()).get(1).isEqual(eventB));
            Assert.assertTrue(test.getEObjectsByID(player.getID()).size() == 2);
        } catch (Errors errors) {
            System.out.println(errors);
        }
    }

}
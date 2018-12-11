package repository;

import configuration.ConfigurationFactory;
import configuration.ConfigurationProtocol;
import configuration.ConfigurationType;
import database.ColumnType;
import database.DatabaseTable;
import database.DatabaseTableManager;
import domain.Player;
import error.Errors;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;


/**
 * Name:        {ClassName}
 * Effect:      {ClassEffect}
 * Date:        01/04/2017
 * Tested:      False
 *
 * @author Alexandru Stoica
 * @version 1.0
 */


public class RepositoryEntityDatabaseTest {

    private RepositoryEntityDatabase<Player, Integer> test;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        String database = "test.db";

        DatabaseTable databaseTable = new DatabaseTable("Player");
        databaseTable.addColumn("ID", ColumnType.ID);
        databaseTable.addColumn("NAME", ColumnType.STRING);
        databaseTable.addColumn("AGE", ColumnType.INT);

        DatabaseTableManager tableManager = new DatabaseTableManager(database, databaseTable);

        try {
            tableManager.makeTable();
            ConfigurationProtocol<Player, Integer> configuration = ConfigurationFactory.build(ConfigurationType.PLAYER);
            this.test = new RepositoryEntityDatabase<>(database, configuration);
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
            ArrayList<Player> data = this.test.getAll();
            Assert.assertTrue(data.isEmpty());
            for (Integer index = 1; index < 100; index++ ) {
                Player player = new Player(index, "Player Test", 10);
                int id = test.add(player);
                data = test.getAll();
                Assert.assertFalse(data.isEmpty());
                Assert.assertTrue(id == index);
                Assert.assertTrue(data.get(index - 1).isEqual(player));
            }
        } catch (Errors errors) {
            Assert.assertFalse(errors.empty());
            System.out.print(errors.getMessage());
        }
    }

    @Test
    public void update() throws Exception {
        try {
            for (Integer index = 1; index < 100; index++ ) {
                Player player = new Player(index, "Player Test", 10);
                test.add(player);
                Player update = new Player(index, "Player Update", 100);
                test.update(player, update);
                Assert.assertTrue(test.getAll().get(index - 1).isEqual(update));
            }
        } catch (Errors errors) {
            Assert.assertFalse(errors.empty());
            System.out.print(errors.getMessage());
        }
    }

    @Test
    public void delete() throws Exception {
        try {
            for (Integer index = 1; index < 100; index++ ) {
                Player player = new Player(index, "Player Test", 10);
                test.add(player);
                Assert.assertTrue(test.getAll().get(0).isEqual(player));
                test.delete(player);
            }
            Assert.assertTrue(test.getAll().isEmpty());
        } catch (Errors errors) {
            Assert.assertFalse(errors.empty());
            System.out.print(errors.getMessage());
        }
    }

    @Test
    public void getAll() throws Exception {
        try {
            Assert.assertTrue(test.getAll().isEmpty());
            Player player;
            for (Integer index = 1; index < 100; index++ ) {
                player = new Player(index, "Player Test", 10);
                test.add(player);
            }
            for (Integer index = 1; index < 100; index++) {
                player = new Player(index, "Player Test", 10);
                Assert.assertTrue(test.getAll().get(index - 1).isEqual(player));
            }
        } catch (Errors errors) {
            Assert.assertFalse(errors.empty());
            System.out.print(errors.getMessage());
        }
    }

    @Test
    public void findElementById() throws Exception {
        try {
            Assert.assertTrue(test.getAll().isEmpty());
            Player player;
            for (Integer index = 1; index < 100; index++ ) {
                player = new Player(index, "Player Test", 10);
                test.add(player);
                Assert.assertTrue(test.getAll().get(index - 1).isEqual(test.findElementById(index)));
            }
        } catch (Errors errors) {
            Assert.assertFalse(errors.empty());
            System.out.print(errors.getMessage());
        }
    }

}
package model;

import domain.User;
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

public class ModelUserTest {


    private ModelUser model;

    @Before
    public void setUp() throws Exception {
        this.model = new ModelUser("test.db");
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("test.db");
        final boolean delete = file.delete();
        assertTrue(delete);
    }

    @Test
    public void add() throws Exception {
        User user = new User(1, "Username", "password");
        model.add(user);
        assertTrue(model.getAll().get(0).isEqual(user) &&
                model.getAll().size() == 1);
    }

    @Test
    public void update() throws Exception {
        User first = new User(1, "Test", "Password");
        User update = new User(1, "Update", "PasswordUpdate");
        model.add(first);
        model.update(first, update);
        assertTrue(model.getAll().get(0).isEqual(update) &&
                model.getAll().size() == 1);
    }

    @Test
    public void delete() throws Exception {
        User first = new User(1, "Test", "Password");
        User second = new User(2, "Update", "Password");
        model.add(first);
        model.add(second);
        model.delete(first);
        assertTrue(model.getAll().get(0).isEqual(second) &&
                model.getAll().size() == 1);
    }

    @Test
    public void getAll() throws Exception {
        User first = new User(1, "Test", "Password");
        User second = new User(2, "Update", "Password");
        model.add(first);
        model.add(second);
        assertTrue(model.getAll().get(0).isEqual(first) &&
                model.getAll().get(1).isEqual(second) &&
                model.getAll().size() == 2);
    }

    @Test
    public void getElementById() throws Exception {
        User user = new User(1, "Username", "Password");
        model.add(user);
        assertTrue(model.getElementById(1).isEqual(user));
    }

}
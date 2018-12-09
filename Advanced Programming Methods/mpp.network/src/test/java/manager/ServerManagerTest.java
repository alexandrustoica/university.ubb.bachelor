package manager;

import model.ModelUser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import service.Service;

import static org.mockito.Mockito.mock;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ServerManagerTest {

    @Rule private MockitoRule userRule;

    private ModelUser modelUser;
    private Service service;

    @Before
    public void setUp() throws Exception {
        userRule = MockitoJUnit.rule();
    }

    @Test
    public void login() throws Exception {

    }

    @Test
    public void signUp() throws Exception {

    }

    @Test
    public void getPlayers() throws Exception {

    }

    @Test
    public void getEventsFromPlayer() throws Exception {

    }

    @Test
    public void getEvents() throws Exception {

    }

    @Test
    public void getPlayerFromEvent() throws Exception {

    }

    @Test
    public void addPlayer() throws Exception {

    }

}
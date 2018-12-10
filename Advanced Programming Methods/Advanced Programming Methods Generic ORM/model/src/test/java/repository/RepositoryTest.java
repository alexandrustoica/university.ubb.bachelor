package repository;

import database.DatabaseLoader;
import database.DatabaseGateway;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.DatabaseType.TEST;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class RepositoryTest {

    private Repository<UserEntity, Integer> repositoryUser;
    private DatabaseGateway loader;

    @Before
    public void setUp() throws Exception {
        DatabaseGateway loader = new DatabaseLoader(TEST);
        repositoryUser = new RepositoryEntity<>(UserEntity.class, loader);
    }

    @Test
    public void isInsertingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity test = new UserEntity(1, "username", "password");
        // when:
        UserEntity inserted = repositoryUser.insert(user);
        // then:
        assertEquals(inserted, test);
    }

    @Test
    public void isUpdatingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity update = new UserEntity("usernameUpdate", "passwordUpdate");
        // when:
        UserEntity inserted = repositoryUser.insert(user);
        UserEntity updated = repositoryUser.update(inserted, update);
        // then:
        assertEquals(updated, update);
    }

    @Test
    public void isDeletingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // preconditions:
        UserEntity inserted = repositoryUser.insert(user);
        // when:
        Optional<UserEntity> deleted = repositoryUser.delete(inserted);
        // then:
        deleted.ifPresent(item -> assertEquals(item, inserted));
        assertTrue(repositoryUser.all().isEmpty());
    }

    @Test
    public void isNotDeletingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // when:
        Optional<UserEntity> deleted = repositoryUser.delete(user);
        // then:
        assertFalse(deleted.isPresent());

    }

    @Test
    public void isGettingElements() throws Exception {
        // declarations:
        UserEntity user = new UserEntity(1, "username", "password");
        UserEntity test = new UserEntity(2, "test", "password");
        List<UserEntity> list = new ArrayList<>();
        // preconditions:
        list.add(repositoryUser.insert(user));
        list.add(repositoryUser.insert(test));
        // when:
        List<UserEntity> result = repositoryUser.all();
        // then:
        assertEquals(result, list);
    }

    @Test
    public void isGettingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // preconditions:
        UserEntity inserted = repositoryUser.insert(user);
        // when:
        Optional<UserEntity> result = repositoryUser.getElementById(1);
        // then:
        result.ifPresent(item -> assertEquals(item, inserted));
    }

    @Test
    public void isNotGettingElement() throws Exception {
        // when:
        Optional<UserEntity> result = repositoryUser.getElementById(1);
        // then:
        assertFalse(result.isPresent());
    }
}
package model;

import database.DatabaseLoader;
import database.DatabaseSessionGateway;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.ConfigurationType.TEST;
import static junit.framework.TestCase.assertEquals;


/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelTest {

    private ModelRelational<UserEntity, Integer> model;

    @Before
    public void setUp() throws Exception {
        DatabaseSessionGateway loader = new DatabaseLoader(TEST);
        model = new ModelRelational<>(UserEntity.class, loader);
    }

    @Test
    public void isAddingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // then:
        UserEntity inserted = model.insert(user);
        // when:
        assertEquals((long)inserted.getId(), 1);
    }

    @Test
    public void isUpdatingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity with = new UserEntity("with", "password");
        UserEntity expected = new UserEntity(1, "with", "password");
        // preconditions:
        user = model.insert(user);
        // when:
        user = model.update(user, with);
        // then:
        assertEquals(user, expected);
    }

    @Test
    public void isDeletingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // preconditions:
        UserEntity inserted = model.insert(user);
        // when:
        Optional<UserEntity> deleted = model.delete(inserted);
        // then:
        deleted.ifPresent(item -> assertEquals(item, inserted));
    }

    @Test
    public void isGettingElements() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        UserEntity test = new UserEntity("username", "password");
        List<UserEntity> list = new ArrayList<>();
        // preconditions:
        list.add(model.insert(user));
        list.add(model.insert(test));
        // when:
        List<UserEntity> results = model.all();
        // then:
        assertEquals(results, list);
    }

    @Test
    public void isGettingElement() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("username", "password");
        // preconditions:
        UserEntity inserted = model.insert(user);
        // when:
        Optional<UserEntity> result = model.getElementById(inserted.getId());
        // then:
        result.ifPresent(item -> assertEquals(item, inserted));
    }
}
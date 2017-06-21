package model;

import database.DatabaseLoader;
import database.DatabaseSessionGateway;
import domain.NotificationEntity;
import domain.UserEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static database.ConfigurationType.TEST;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelOneToManyTest {

    private ModelOneToMany<UserEntity, NotificationEntity, Integer> model;

    @Before
    public void setUp() throws Exception {
        DatabaseSessionGateway loader = new DatabaseLoader(TEST);
        ConfigurationModel<UserEntity, NotificationEntity> configuration =
                new ConfigurationModelOneToMany<>(NotificationEntity::getUser, NotificationEntity::setUser);
        model = new ModelRelationalOneToMany<>(configuration, UserEntity.class, NotificationEntity.class, loader);
    }

    @Test
    public void isInsertingElementInRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        // preconditions:
        UserEntity inserted = model.insert(user);
        // when:
        Optional<UserEntity> result =  model.insert(inserted, notification);
        // then:
        result.ifPresent(item -> assertTrue(item.getNotifications().stream()
                .anyMatch(element -> element.getText().equals(notification.getText()))));
    }

    @Test
    public void isDeletingElementInRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        // preconditions:
        UserEntity inserted = model.insert(user);
        // when:
        Optional<UserEntity> result =  model.insert(inserted, notification);
        // then:
        result.ifPresent(item -> assertTrue(item.getNotifications().size() == 1));
        // when:
        result = model.delete(inserted, notification);
        // then:
        result.ifPresent(item -> assertTrue(item.getNotifications().isEmpty()));
    }

    @Test
    public void isNotInsertingElementRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        // then:
        assertFalse(model.insert(user, notification).isPresent());
    }

    @Test
    public void isNotDeletingElementRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        // then:
        assertFalse(model.delete(user, notification).isPresent());
    }

    @Test
    public void isUpdatingElementRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        NotificationEntity update = new NotificationEntity("update");
        // preconditions:
        UserEntity inserted = model.insert(user);
        Optional<UserEntity> result =  model.insert(inserted, notification);
        // when:
        Optional<UserEntity> updated = model.update(result.get(), notification, update);
        // then:
        updated.ifPresent(item -> assertTrue(item.getNotifications().stream()
                .anyMatch(element -> element.getText().equals(update.getText()))));
    }

    @Test
    public void isNotUpdatingElementRelationship() throws Exception {
        // declarations:
        UserEntity user = new UserEntity("test", "password");
        NotificationEntity notification = new NotificationEntity("test");
        NotificationEntity update = new NotificationEntity("update");
        // then:
        assertFalse(model.update(user, notification, update).isPresent());
    }
}
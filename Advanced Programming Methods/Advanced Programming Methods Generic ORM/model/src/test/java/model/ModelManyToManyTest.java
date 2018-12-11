package model;

import database.DatabaseLoader;
import database.DatabaseGateway;
import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static database.DatabaseType.TEST;
import static org.junit.Assert.*;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ModelManyToManyTest {

    private ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> model;

    @Before
    public void setUp() throws Exception {
        DatabaseGateway loader = new DatabaseLoader(TEST);
        model = new ModelRelationalManyToMany<>(ProjectEntity.class, TaskEntity.class, ProjectTaskEntity.class, loader);
    }

    @Test
    public void isInsertingRelation() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // preconditions:
        ProjectEntity inserted = model.insert(project);
        TaskEntity added = model.add(task);
        // when:
        Pair<Optional<ProjectEntity>, Optional<TaskEntity>> result = model.insert(inserted, added);
        // then:
        result.getKey().ifPresent(item -> assertTrue(inserted.equals(item) &&
                item.getTasks().stream().anyMatch(element -> element.getId().equals(task.getId()))));
        result.getValue().ifPresent(item -> assertTrue(added.equals(item) &&
                item.getProjects().stream().anyMatch(element -> element.getId().equals(project.getId()))));
    }

    @Test
    public void isNotInsertingElement() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // when:
        Pair<Optional<ProjectEntity>, Optional<TaskEntity>> result = model.insert(project, task);
        // then:
        assertFalse(result.getValue().isPresent() && result.getKey().isPresent());
    }

    @Test
    public void isDeletingRelation() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // preconditions:
        ProjectEntity inserted = model.insert(project);
        TaskEntity added = model.add(task);
        model.insert(inserted, added);
        // when:
        Pair<Optional<ProjectEntity>, Optional<TaskEntity>> result = model.delete(inserted, added);
        // then:
        result.getKey().ifPresent(item -> assertEquals(item.getTasks().size(), 0));
        result.getValue().ifPresent(item -> assertEquals(item.getProjects().size(), 0));
    }

    @Test
    public void isNotDeletingRelation() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // preconditions:
        project = model.insert(project);
        task = model.add(task);
        // when:
        Pair<Optional<ProjectEntity>, Optional<TaskEntity>> result = model.delete(project, task);
        // then:
        assertFalse(result.getKey().isPresent() && result.getValue().isPresent());
    }

    @Test
    public void isUpdatingElements() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // preconditions:
        project = model.insert(project);
        task = model.add(task);
        // when:
        project = model.update(project, new ProjectEntity("update"));
        task = model.refresh(task, new TaskEntity("update"));
        // then:
        model.getElementById(project.getId()).ifPresent(item -> assertEquals(item.getName(), "update"));
        model.receiveElementById(task.getId()).ifPresent(item -> assertEquals(item.getText(), "update"));
    }

    @Test
    public void isDeletingElement() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        // preconditions:
        project = model.insert(project);
        task = model.add(task);
        model.insert(project, task);
        // when:
        Optional<TaskEntity> result = model.remove(task);
        // then:
        assertTrue(result.isPresent());
        result.ifPresent(item -> assertEquals(item.getText(), "test"));
        assertEquals(model.every().size(), 0);
        model.getElementById(project.getId())
                .ifPresent(item -> assertEquals(item.getTasks().size(), 0));
    }

    @Test
    public void isGettingEveryFrom() throws Exception {
        // declarations:
        ProjectEntity project = new ProjectEntity("test");
        TaskEntity task = new TaskEntity("test");
        TaskEntity test = new TaskEntity("test");
        List<TaskEntity> tasks = new ArrayList<>();
        // preconditions:
        project = model.insert(project);
        model.add(task);
        model.add(test);
        model.insert(project, task);
        model.insert(project, test);
        tasks.add(model.receiveElementById(task.getId()).orElse(null));
        tasks.add(model.receiveElementById(test.getId()).orElse(null));
        // then:
        assertEquals(model.everyFrom(project), tasks);
    }

    @Test
    public void isGettingAllFrom() throws Exception {
        // declarations:
        TaskEntity task = new TaskEntity("test");
        ProjectEntity project = new ProjectEntity("test");
        ProjectEntity test = new ProjectEntity("test");
        List<ProjectEntity> projects = new ArrayList<>();
        // preconditions:
        model.add(task);
        model.insert(test);
        model.insert(project);
        model.insert(project, task);
        model.insert(test, task);
        projects.add(model.getElementById(project.getId()).orElse(null));
        projects.add(model.getElementById(test.getId()).orElse(null));
        // then:
        assertEquals(model.allFrom(task), projects);
    }
}
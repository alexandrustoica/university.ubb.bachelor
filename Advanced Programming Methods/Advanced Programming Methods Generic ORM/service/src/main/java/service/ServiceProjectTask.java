package service;

import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import dto.Project;
import dto.Task;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public interface ServiceProjectTask
        extends ServiceManyToMany<Project, Task, ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> {
}

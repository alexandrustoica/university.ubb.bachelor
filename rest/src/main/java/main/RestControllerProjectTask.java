package main;

import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ServiceProjectTask;
import transfarable.Project;
import transfarable.Task;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/remote/project-task")
public class RestControllerProjectTask
        extends ManyToManyRestController<Project, Task, ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> {

    public RestControllerProjectTask(ServiceProjectTask model) {
        super(model);
    }
}

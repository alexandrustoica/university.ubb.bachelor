package special;

import controller.ManyToManyController;
import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import dto.Project;
import dto.Task;
import model.ModelManyToMany;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import translator.ProjectTranslator;
import translator.TaskTranslator;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project-task/")
public class ProjectTaskController
        extends ManyToManyController<Project, Task, ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> {

    public ProjectTaskController(final ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> model) {
        super(model);
        this.translatorT = new ProjectTranslator();
        this.translatorU = new TaskTranslator();
    }
}

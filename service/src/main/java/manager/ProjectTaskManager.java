package manager;

import domain.ProjectEntity;
import domain.ProjectTaskEntity;
import domain.TaskEntity;
import model.ModelManyToMany;
import service.RemoteNotificationCenterService;
import service.ServiceProjectTask;
import transfarable.Project;
import transfarable.Task;
import translator.ProjectTranslator;
import translator.TaskTranslator;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ProjectTaskManager
        extends ManyToManyManager<Project, Task, ProjectEntity, TaskEntity, ProjectTaskEntity, Integer>
        implements ServiceProjectTask {

    public ProjectTaskManager(final ModelManyToMany<ProjectEntity, TaskEntity, ProjectTaskEntity, Integer> model,
                              final RemoteNotificationCenterService center) {
        super(model, center);
        super.translatorT = new ProjectTranslator();
        super.translatorU = new TaskTranslator();
    }
}

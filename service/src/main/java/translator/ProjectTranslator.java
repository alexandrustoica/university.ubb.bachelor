package translator;

import domain.ProjectEntity;
import org.jetbrains.annotations.NotNull;
import transfarable.Project;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class ProjectTranslator implements GenericTranslator<ProjectEntity, Project> {

    @Override
    public Project translate(@NotNull ProjectEntity project) {
        return new Project(project.getId(), project.getName());
    }

    @Override
    public ProjectEntity transform(@NotNull Project project) {
        return new ProjectEntity(project.getId(), project.getName());
    }
}

package translator;

import domain.TaskEntity;
import org.jetbrains.annotations.NotNull;
import dto.Task;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class TaskTranslator implements GenericTranslator<TaskEntity, Task>{

    @Override
    public Task translate(@NotNull TaskEntity task) {
        return new Task(task.getId(), task.getText());
    }

    @Override
    public TaskEntity transform(@NotNull Task task) {
        return new TaskEntity(task.getId(), task.getText());
    }
}

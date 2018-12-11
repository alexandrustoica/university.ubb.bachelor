package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("SameParameterValue") @Entity
@Table(name = "PROJECT_TASK")
public class ProjectTaskEntity implements RelationEntity<ProjectEntity, TaskEntity>, Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_PROJECT_TASK")
    private Integer id;

    private static final Integer DEFAULT_ID = 0;

    @ManyToOne(targetEntity = ProjectEntity.class)
    @JoinColumn(name = "ID_PROJECT", updatable = false)
    private final ProjectEntity project;

    @ManyToOne(targetEntity = TaskEntity.class)
    @JoinColumn(name = "ID_TASK", updatable = false)
    private final TaskEntity task;

    public ProjectTaskEntity() {
        this(DEFAULT_ID, null, null);
    }

    public ProjectTaskEntity(ProjectEntity project, TaskEntity tag) {
        this(DEFAULT_ID, project, tag);
    }

    public ProjectTaskEntity(Integer id, ProjectEntity project, TaskEntity tag) {
        this.id = id;
        this.project = project;
        this.task = tag;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public TaskEntity getTask() {
        return task;
    }

    @Override
    public ProjectEntity supplyT() {
        return getProject();
    }

    @Override
    public TaskEntity supplyU() {
        return getTask();
    }

    @Override
    public RelationEntity<ProjectEntity, TaskEntity> create(ProjectEntity element, TaskEntity item) {
        return new ProjectTaskEntity(element, item);
    }

    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof ProjectTaskEntity)) return false;
        ProjectTaskEntity that = (ProjectTaskEntity) other;
        return getId().equals(that.getId());
    }

    @Override public int hashCode() {
        return getId().hashCode();
    }
}


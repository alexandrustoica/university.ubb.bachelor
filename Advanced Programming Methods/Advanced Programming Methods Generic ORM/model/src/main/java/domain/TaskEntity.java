package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@SuppressWarnings("SameParameterValue") @Entity
@Table(name = "TASK")
public class TaskEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_TASK")
    private Integer id;

    @Column(name = "TEXT")
    private final String text;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_TEXT = "";

    @OneToMany(targetEntity = ProjectTaskEntity.class, fetch = FetchType.EAGER,
               mappedBy = "task", cascade = CascadeType.ALL)
    private final Set<ProjectTaskEntity> projects;

    public TaskEntity() {
        this(DEFAULT_TEXT);
    }

    public TaskEntity(String text) {
        this(DEFAULT_ID, text);
    }

    public TaskEntity(Integer id, String text) {
        this.id = id;
        this.text = text;
        projects = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public List<ProjectEntity> getProjects() {
        return projects.stream()
                .map(ProjectTaskEntity::getProject)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TaskEntity)) return false;
        TaskEntity that = (TaskEntity) other;
        return getId().equals(that.getId()) && getText().equals(that.getText());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }
}

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
@Table(name = "PROJECT")
public class ProjectEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_PROJECT")
    private Integer id;

    @Column(name = "NAME")
    private final String name;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_NAME = "";

    @OneToMany(targetEntity = ProjectTaskEntity.class, mappedBy = "project",
               fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final Set<ProjectTaskEntity> tasks;

    public ProjectEntity() {
        this(DEFAULT_ID, DEFAULT_NAME);
    }

    public ProjectEntity(String name) {
        this(DEFAULT_ID, name);
    }

    public ProjectEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<TaskEntity> getTasks() {
        return tasks.stream()
                .map(ProjectTaskEntity::getTask)
                .collect(Collectors.toList());
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectEntity)) return false;
        ProjectEntity that = (ProjectEntity) o;
        return getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}


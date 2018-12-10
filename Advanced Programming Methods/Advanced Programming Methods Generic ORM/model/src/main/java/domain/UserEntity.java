package domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "USER")
@SuppressWarnings("WeakerAccess")
public class UserEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_USER")
    private Integer id;

    @Column(name = "USERNAME")
    private final String username;

    @Column(name = "PASSWORD")
    private final String password;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_STRING = "";

    @OneToMany(targetEntity = NotificationEntity.class, fetch = FetchType.EAGER,
               mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<NotificationEntity> notifications = new HashSet<>();

    @SuppressWarnings("unused")
    public UserEntity() {
        this(DEFAULT_STRING, DEFAULT_STRING);
    }

    public UserEntity(String username, String password) {
        this(DEFAULT_ID, username, password);
    }

    public UserEntity(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<NotificationEntity> getNotifications() {
        return notifications;
    }

    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) other;
        return getId().equals(that.getId())
                && getUsername().equals(that.getUsername())
                && getPassword().equals(that.getPassword());
    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        return result;
    }
}

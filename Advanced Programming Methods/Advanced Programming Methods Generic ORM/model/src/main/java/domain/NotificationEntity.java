package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.1
 */

@Entity
@Table(name = "NOTIFICATION")
@SuppressWarnings("ALL")
public class NotificationEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_NOTIFICATION")
    private Integer id;

    @Column(name = "TEXT")
    private String text;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_TEXT = "";

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "ID_USER", updatable = false)
    private UserEntity user;

    public NotificationEntity() {
        this(DEFAULT_ID, DEFAULT_TEXT);
    }

    public NotificationEntity(String text) {
        this(DEFAULT_ID, text);
    }

    public NotificationEntity(Integer id, String text) {
        this.id = id;
        this.text = text;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationEntity)) return false;
        NotificationEntity that = (NotificationEntity) o;
        return getId().equals(that.getId()) && getText().equals(that.getText());
    }

    @Override public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getText().hashCode();
        return result;
    }
}

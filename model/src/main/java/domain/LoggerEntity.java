package domain;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "LOGGER")
@SuppressWarnings({"unused", "WeakerAccess"})
public class LoggerEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_USER")
    private Integer idUser;

    @Column(name = "HOST_USER")
    private String host;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_HOST = "";

    public LoggerEntity() {
        this(DEFAULT_ID, DEFAULT_HOST);
    }

    public LoggerEntity(@NotNull final Integer idUser, @NotNull final String host) {
        this(DEFAULT_ID, idUser, host);
    }

    public LoggerEntity(@NotNull final Integer id, @NotNull final Integer idUser, @NotNull final String host) {
        this.id = id;
        this.idUser = idUser;
        this.host = host;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public Integer getIdUser() {
        return idUser;
    }
}

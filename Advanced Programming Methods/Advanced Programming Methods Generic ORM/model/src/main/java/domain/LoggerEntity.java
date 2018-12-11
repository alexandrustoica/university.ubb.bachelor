package domain;

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

    @Column(name = "ID_CLIENT")
    private Integer idClient;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_HOST = "";

    public LoggerEntity() {
        this(DEFAULT_ID, DEFAULT_HOST, DEFAULT_ID);
    }

    public LoggerEntity(Integer idUser, String host, Integer idClient) {
        this(DEFAULT_ID, idUser, host, idClient);
    }

    public LoggerEntity(Integer id, Integer idUser, String host, Integer idClient) {
        this.id = id;
        this.idUser = idUser;
        this.host = host;
        this.idClient = idClient;
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

    public Integer getIdClient() {
        return idClient;
    }

}

package domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@Entity
@Table(name = "BOARD")
@SuppressWarnings({"unused", "WeakerAccess"})
public class BoardEntity implements Idable<Integer> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "TEXT")
    private String text;

    private static final Integer DEFAULT_ID = 0;
    private static final String DEFAULT_TEXT = "";

    public BoardEntity() {
        this(DEFAULT_TEXT, DEFAULT_TEXT);
    }

    public BoardEntity(String fileUrl, String text) {
        this(DEFAULT_ID, fileUrl, text);
    }

    public BoardEntity(Integer id, String fileUrl, String text) {
        this.id = id;
        this.fileUrl = fileUrl;
        this.text = text;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getFileUrl() {
        return fileUrl;
    }
}

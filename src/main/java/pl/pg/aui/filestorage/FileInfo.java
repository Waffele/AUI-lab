package pl.pg.aui.filestorage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String author;
    private String filename;

    public FileInfo(Long id, String author, String filename) {
        this.id = id;
        this.author = author;
        this.filename = filename;
    }

    public FileInfo(String author, String filename) {
        this(null, author, filename);
    }

    public FileInfo() {
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getFilename() {
        return filename;
    }
}

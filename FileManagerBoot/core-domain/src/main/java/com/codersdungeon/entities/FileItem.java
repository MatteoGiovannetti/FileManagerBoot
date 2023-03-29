package com.codersdungeon.entities;

import com.codersdungeon.dto.FileType;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class FileItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String filename;

    @Column(nullable = false)
    private Long fileSize;

  /*  @ManyToOne
    @JoinColumn(name="name")
    private Directory directory;*/

    @Column (nullable = false)
    private Instant creationDate;

    @Column (nullable = false)
    private FileType fileType = FileType.FILE;

    @ManyToOne
   // @JoinColumn (name = "username")
    private User fileOwner;

    public FileItem(String filename, Long fileSize, Directory directory, Instant creationDate, FileType fileType, User fileOwner) {
        this.filename = filename;
        this.fileSize = fileSize;
       // this.directory = directory;
        this.creationDate = creationDate;
        this.fileType = fileType;
        this.fileOwner = fileOwner;
    }

    public FileItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String name) {
        this.filename = name;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long dimension) {
        this.fileSize = dimension;
    }

   /* public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }*/

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public User getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(User owner) {
        this.fileOwner = owner;
    }
}

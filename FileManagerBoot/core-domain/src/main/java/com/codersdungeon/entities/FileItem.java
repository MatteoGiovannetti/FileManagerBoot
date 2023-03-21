package com.codersdungeon.entities;

import com.codersdungeon.dto.Type;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FileItem {

    @Id
    private String name;

    @Column (nullable = false)
    private Long dimension;

    @ManyToOne
    @JoinColumn(name="directory_name", nullable = false)
    private Directory directory;

    @Column (nullable = false)
    private Date creationDate;

    @Column (nullable = false)
    private Type type = Type.FILE;

    @ManyToOne
    @JoinColumn (name = "username")
    private User owner;

    public FileItem(String name, Long dimension, Directory directory, Date creationDate, Type type, User owner) {
        this.name = name;
        this.dimension = dimension;
        this.directory = directory;
        this.creationDate = creationDate;
        this.type = type;
        this.owner = owner;
    }

    public FileItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDimension() {
        return dimension;
    }

    public void setDimension(Long dimension) {
        this.dimension = dimension;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

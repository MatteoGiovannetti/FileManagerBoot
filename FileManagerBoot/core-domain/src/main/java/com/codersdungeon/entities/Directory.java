package com.codersdungeon.entities;

import com.codersdungeon.dto.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Directory extends FileItem{

    @OneToMany
    private List<FileItem> fileItemList = new ArrayList<>();

    public Directory(String name, Long dimension, Directory directory, Date creationDate, Type type, User owner, List<FileItem> fileItemList) {
        super(name, dimension, directory, creationDate, type, owner);
        this.fileItemList = fileItemList;
        this.setType(Type.FOLDER);
    }

    public Directory(List<FileItem> fileItemList) {
        this.fileItemList = fileItemList;
    }

    public Directory() {
    }

}

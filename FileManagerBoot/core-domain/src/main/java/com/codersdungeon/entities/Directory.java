package com.codersdungeon.entities;

import com.codersdungeon.dto.FileType;


import java.time.Instant;
import java.util.ArrayList;

import java.util.List;


public class Directory extends FileItem{


    private List<FileItem> fileItemList = new ArrayList<>();

    public Directory(String name, Long dimension, Directory directory, Instant creationDate, FileType fileType, User owner, List<FileItem> fileItemList) {
        super(name, dimension, directory, creationDate, fileType, owner);
        this.fileItemList = fileItemList;
        this.setFileType(FileType.FOLDER);
    }

    public Directory(List<FileItem> fileItemList) {
        this.fileItemList = fileItemList;
    }

    public Directory() {
    }

}

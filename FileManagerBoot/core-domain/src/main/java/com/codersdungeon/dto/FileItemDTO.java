package com.codersdungeon.dto;
import com.codersdungeon.entities.User;

import java.time.Instant;
import java.util.Date;

public class FileItemDTO {

    public Integer id;

    public String name;

    public Long dimension;
    public DirectoryDTO directory;
    public Instant creationDate;
    public FileType type;
    public String owner;


}

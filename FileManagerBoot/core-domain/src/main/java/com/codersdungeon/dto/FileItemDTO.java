package com.codersdungeon.dto;
import com.codersdungeon.entities.User;

import java.time.Instant;
import java.util.Date;

public class FileItemDTO {

    public String name;

    public Enum type;
    public Long dimension;
    public DirectoryDTO directory;
    public Instant creationDate;
    public User owner;
}

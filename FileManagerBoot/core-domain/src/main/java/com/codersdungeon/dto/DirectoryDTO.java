package com.codersdungeon.dto;

import com.codersdungeon.entities.User;

import java.util.Date;
import java.util.List;

public class DirectoryDTO {

    public String name;

    public Enum type;
    public Long dimension;
    public DirectoryDTO directory;
    public Date creationDate;
    public User owner;
    public ListFileItemDTO fileItemDTOS;
}

package com.codersdungeon.entities;

import java.util.ArrayList;
import java.util.List;

public class ListFileItem {

    private List<FileItem> items = new ArrayList<>();

    public List<FileItem> getItems() {
        return items;
    }

    public void setItems(List<FileItem> items) {
        this.items = items;
    }
}

package com.codersdungeon.dto;

import java.util.ArrayList;
import java.util.List;

public class ListFileItemDTO {

    public List<FileItemDTO> items = new ArrayList<>();

    @Override
    public String toString() {
        return "ListFileItemDTO{" +
                "items=" + items +
                '}';
    }


}

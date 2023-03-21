package com.codersdungeon.abstraction;

import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;

public interface AppInterface {

    ListFileItemDTO findFilesInDirectory(String percorso);

   ListFileItemDTO findFilesSubDir(String percorso);

    FileItemDTO copyItem(String filename, String directory, String destination);

    void deleteItem(String directory, String filename);

    ListFileItemDTO backup(String destination);

}

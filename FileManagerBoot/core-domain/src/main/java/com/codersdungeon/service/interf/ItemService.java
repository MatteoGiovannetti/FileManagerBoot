package com.codersdungeon.service.interf;

import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;

public interface ItemService {

    ListFileItemDTO findAllFiles(String percorso);

    FileItemDTO findFile(String fileItem);
    DirectoryDTO findDirectory(String directory);

    ListFileItemDTO findAllSubDir(String percorso);

    FileItemDTO copyItem(FileItemDTO  filename, DirectoryDTO directory, DirectoryDTO destination);

    void deleteItem(DirectoryDTO directory, FileItemDTO filename);

    ListFileItemDTO backup(DirectoryDTO directory, DirectoryDTO destination);
}

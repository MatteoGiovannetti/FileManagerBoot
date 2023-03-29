package com.codersdungeon.service.interf;

import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
import com.codersdungeon.dto.FileType;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.entities.FileItem;
import com.codersdungeon.entities.User;

import java.time.Instant;
import java.util.List;

public interface ItemService {

    ListFileItemDTO findAllFiles(String percorso);

    FileItemDTO findFile(String fileItem, String directory);
    DirectoryDTO findDirectory(String directory);

    ListFileItemDTO findAllSubDir(String percorso);

    FileItemDTO copyItem(FileItemDTO  filename, DirectoryDTO directory, DirectoryDTO destination);

    void deleteItem(DirectoryDTO directory, FileItemDTO filename);

    ListFileItemDTO backup(DirectoryDTO directory, DirectoryDTO destination);

    FileItemDTO createFile(String name, Long dimension, Directory dir, Instant creationDate, FileType fileType,
                           String owner);

    DirectoryDTO createDir(String name, Long dimension, Directory dir, Instant creationDate, FileType fileType,
                           User owner, List<FileItem> items);

}

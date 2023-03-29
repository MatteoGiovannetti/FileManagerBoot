package com.codersdungeon.service.impl;

import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
import com.codersdungeon.dto.FileType;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.entities.FileItem;
import com.codersdungeon.entities.User;
import com.codersdungeon.exceptions.EmptyPathException;
import com.codersdungeon.exceptions.PathNotFoundException;
import com.codersdungeon.exceptions.PercorsoNullException;
import com.codersdungeon.repositories.ItemRepository;
import com.codersdungeon.repositories.UserRepository;
import com.codersdungeon.service.interf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codersdungeon.dto.FileType.FILE;
import static com.codersdungeon.dto.FileType.FOLDER;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ListFileItemDTO findAllFiles(String percorso) {

        if(percorso==null){
            throw new PercorsoNullException("Percorso non valido");
        }

        if(percorso.trim().isEmpty()){
            throw new EmptyPathException("Percorso non valido");
        }

        ListFileItemDTO listFileItemDTO = new ListFileItemDTO();

        String workDir = System.getProperty("user.dir");
        Path path = Paths.get(workDir,percorso);

        try {
            listFileItemDTO.items = Files.list(path).map(this::pathToDTO).
                    filter(Optional::isPresent).
                    map(Optional::get).
                    collect(Collectors.toList());
        } catch (IOException e) {
            throw new PathNotFoundException(e);
        }

        return listFileItemDTO;
    }

    @Override
    public FileItemDTO findFile(String fileItem, String directory){
            FileItemDTO dto = fileToDTO(itemRepository.findFileByNameAndFolder(fileItem));
            return dto;
    }

    @Override
    public DirectoryDTO findDirectory(String directory) {
        DirectoryDTO dto = dirToDTO(itemRepository.findDirByName(directory));
        return dto;
    }

    @Override
    public ListFileItemDTO findAllSubDir(String percorso) // qui tentativo di ricorsione
    {

        if(percorso==null){
            throw new PercorsoNullException("Percorso non valido");
        }

        if(percorso.trim().isEmpty()){
            throw new EmptyPathException("Percorso non valido");
        }

        ListFileItemDTO listFileItemDTO = new ListFileItemDTO();

        String workDir = System.getProperty("user.dir");
        Path path = Paths.get(workDir,percorso);

        try {
            listFileItemDTO.items = Files.list(path).map(this::pathToDTO).
                    filter(Optional::isPresent).
                    map(Optional::get).
                    collect(Collectors.toList());
        } catch (IOException e) {
            throw new PathNotFoundException(e);
        }

        for(FileItemDTO item : listFileItemDTO.items) { //ricorsione valida?
            if (item.type==FOLDER) {
                String perc = workDir +"/"+ item.name; //sta cosa?
                findAllSubDir(perc);
            }
        }
        return listFileItemDTO;
    }

    @Override
    public FileItemDTO copyItem(FileItemDTO filename, DirectoryDTO directory, DirectoryDTO destination) {
        FileItem base = itemRepository.findFileByNameAndFolder(filename.name);
        FileItem copy = new FileItem(base.getFilename(), base.getFileSize(), null,
                Instant.now(), base.getFileType(), base.getFileOwner());
        itemRepository.save(copy);
        return fileToDTO(copy);
    }

    @Override
    public void deleteItem(DirectoryDTO directory, FileItemDTO filename) {
        FileItem item = itemRepository.findFileByNameAndFolder(filename.name);
        itemRepository.delete(item);
    }

    @Override
    public ListFileItemDTO backup(DirectoryDTO directory, DirectoryDTO destination) {
        DirectoryDTO dirDTO = dirToDTO(itemRepository.findDirByName(directory.name));
        DirectoryDTO destDTO = dirToDTO(itemRepository.findDirByName(destination.name));
        destDTO.fileItemDTOS.items.addAll(dirDTO.fileItemDTOS.items);
        return destDTO.fileItemDTOS;
    }

    @Override
    public FileItemDTO createFile(String name, Long dimension, Directory dir, Instant creationDate,
                                  FileType fileType,
                                  String owner){
        User user = userRepository.findById(owner).orElse(null);
        FileItem item = new FileItem(name, dimension,dir,creationDate, fileType, user);
        return fileToDTO(itemRepository.save(item));
    }

    @Override
    public DirectoryDTO createDir(String name, Long dimension, Directory dir, Instant creationDate, FileType fileType,
                                  User owner, List<FileItem> items) {
        Directory directory = new Directory(name, dimension, dir, creationDate, fileType, owner, items);
        return dirToDTO(itemRepository.save(directory));
    }

    private Optional<FileItemDTO> pathToDTO (Path path) {
        FileItemDTO itemDTO = new FileItemDTO();
        itemDTO.name = path.getFileName().toString();
        itemDTO.type = Files.isDirectory(path)?FOLDER:FILE;
        if(!(itemDTO.type == FOLDER)) {
            try {
                itemDTO.dimension = Files.size(path);
            } catch (IOException e) {
                e.printStackTrace();
                // throw new RuntimeException(e);
                return Optional.empty();
            }
        }
        return Optional.of(itemDTO);
    }

    private FileItemDTO fileToDTO (FileItem item) {
        FileItemDTO fileItemDTO = new FileItemDTO();
        fileItemDTO.name = item.getFilename();
        fileItemDTO.dimension = item.getFileSize();
       // fileItemDTO.directory = dirToDTO(null);
        fileItemDTO.creationDate = item.getCreationDate();
        fileItemDTO.type = item.getFileType();
        fileItemDTO.owner = item.getFileOwner().getUsername();
        return fileItemDTO;
    }

    private DirectoryDTO dirToDTO(FileItem dir) {
        DirectoryDTO dirItemDTO = new DirectoryDTO();
        dirItemDTO.name = dir.getFilename();
        dirItemDTO.dimension = dir.getFileSize();
        dirItemDTO.directory = dirToDTO(null);
        dirItemDTO.creationDate = dir.getCreationDate();
        dirItemDTO.type = dir.getFileType();
        dirItemDTO.owner = dir.getFileOwner();
        return dirItemDTO;
    }
}

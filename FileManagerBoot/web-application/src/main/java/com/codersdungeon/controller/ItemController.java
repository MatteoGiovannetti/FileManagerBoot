package com.codersdungeon.controller;

import com.codersdungeon.abstraction.AppInterface;
import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
import com.codersdungeon.dto.FileType;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.entities.ListFileItem;
import com.codersdungeon.entities.User;

import com.codersdungeon.service.interf.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/file-manager")
public class ItemController implements AppInterface {

    @Autowired
    ItemService utenteService;

   /* @GetMapping ("/items/{percorso}")
    public ListFileItemDTO findAll(@PathVariable("percorso") String percorso) {
        return utenteService.findAllFiles(percorso);
    }*/

    @Override
    @GetMapping(("/items/{percorso}"))
    public ListFileItemDTO findFilesInDirectory(@PathVariable("percorso") String percorso) {
         return utenteService.findAllFiles(percorso);
    }

    @Override
    @GetMapping("/items/sub/{percorso}")
    public ListFileItemDTO findFilesSubDir(@PathVariable("percorso") String percorso ) {
        return utenteService.findAllSubDir(percorso);
    }

    @Override
    @PostMapping("/item/copy/{directory}/{filename}/{destination}")
    public FileItemDTO copyItem(@PathVariable("filename") String filename,
                                @PathVariable("directory") String directory,
                                @PathVariable("destination") String destination) {
        FileItemDTO fileItemDTO = utenteService.findFile(filename, directory);
        DirectoryDTO destDTO = utenteService.findDirectory(destination);
        return utenteService.copyItem(fileItemDTO, fileItemDTO.directory, destDTO);
    }

    @Override
    @DeleteMapping("/item/delete/{directory}/{filename}")
    public void deleteItem(@PathVariable("directory") String directory,
                           @PathVariable("filename") String filename) {
        FileItemDTO fileItemDTO = utenteService.findFile(filename, directory);
        utenteService.deleteItem(fileItemDTO.directory, fileItemDTO);
    } //TODO controllare

    //TODO cane da pisciare
    //FIXME errori da correere

    @Override
    @PostMapping("/{directory}/backup/{destination}")
    public ListFileItemDTO backup(@PathVariable ("directory") String directory,
                                  @PathVariable("destination") String destination) {
        DirectoryDTO directoryDTO = utenteService.findDirectory(directory);
        DirectoryDTO destinationDTO =utenteService.findDirectory(destination);
        return utenteService.backup(directoryDTO, destinationDTO);
    }

    @PostMapping("/create/file")

    public FileItemDTO saveFile(@RequestBody FileItemDTO dto
                       ){
        return utenteService.createFile(dto.name, dto.dimension, null, Instant.now(),
                dto.type, dto.owner);
    }

    @PostMapping("/create/directory")
    public DirectoryDTO saveDirectory(@PathVariable("name") String name,
                           @PathVariable("dimension") Long dimension,
                           @PathVariable("directory")Directory directory,
                           @PathVariable("creationDate") Instant creationDate,
                           @PathVariable("type") FileType fileType,
                           @PathVariable("owner")User owner,
                           @PathVariable("items")ListFileItem listFileItem
                           ){
        return utenteService.createDir(name,dimension, directory, creationDate, fileType, owner, listFileItem.getItems());
    }


}

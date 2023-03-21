package codersdungeon.controller;

import com.codersdungeon.abstraction.AppInterface;
import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
import com.codersdungeon.dto.Type;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.entities.ListFileItem;
import com.codersdungeon.entities.User;
import com.codersdungeon.repositories.ItemRepository;
import com.codersdungeon.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/file-manager")
public class ItemController implements AppInterface {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemServiceImpl utenteService;

   /* @GetMapping ("/items/{percorso}")
    public ListFileItemDTO findAll(@PathVariable("percorso") String percorso) {
        return utenteService.findAllFiles(percorso);
    }*/

    @Override
    @GetMapping(("/items/{percorso}"))
    public ListFileItemDTO findFilesInDirectory(@PathVariable("percorso") String percorso ) {
         return utenteService.findAllFiles(percorso);
    }

    @Override
    @GetMapping("items/sub/{percorso}")
    public ListFileItemDTO findFilesSubDir(@PathVariable("percorso") String percorso ) {
        return utenteService.findAllSubDir(percorso);
    }

    @Override
    @PostMapping("item/copy/{directory}/{filename}/{destination}")
    public FileItemDTO copyItem(@PathVariable("filename") String filename,
                                @PathVariable("directory") String directory,
                                @PathVariable("destination") String destination) {
        FileItemDTO fileItemDTO = utenteService.findFile(filename);
        DirectoryDTO directoryDTO =utenteService.findDirectory(directory);
        DirectoryDTO destDTO = utenteService.findDirectory(destination);
        return utenteService.copyItem(fileItemDTO, directoryDTO, destDTO);
    }

    @Override
    @DeleteMapping("item/delete/{directory}/{filename}")
    public void deleteItem(@PathVariable("directory") String directory,
                           @PathVariable("filename") String filename) {
        FileItemDTO fileItemDTO = utenteService.findFile(filename);
        DirectoryDTO directoryDTO =utenteService.findDirectory(directory);
        utenteService.deleteItem(directoryDTO, fileItemDTO);
    }

    @Override
    @PostMapping("backup/{destination}")
    public ListFileItemDTO backup(@PathVariable("destination") String destination) {
        DirectoryDTO directoryDTO =utenteService.findDirectory(destination);
        return utenteService.backup(directoryDTO);
    }

    @PostMapping("create/file")
    public FileItemDTO saveFile(@PathVariable("name") String name,
                       @PathVariable("dimension") Long dimension,
                       @PathVariable("directory")Directory directory,
                       @PathVariable("creationDate") Date creationDate,
                       @PathVariable("type") Type type,
                       @PathVariable("owner")User owner
                       ){
        return utenteService.createFile(name, dimension, directory, creationDate, type, owner);
    }

    @PostMapping("create/directory")
    public DirectoryDTO saveDirectory(@PathVariable("name") String name,
                           @PathVariable("dimension") Long dimension,
                           @PathVariable("directory")Directory directory,
                           @PathVariable("creationDate") Date creationDate,
                           @PathVariable("type") Type type,
                           @PathVariable("owner")User owner,
                           @PathVariable("items")ListFileItem listFileItem
                           ){
        return utenteService.createDir(name,dimension, directory, creationDate, type, owner, listFileItem.getItems());
    }


}

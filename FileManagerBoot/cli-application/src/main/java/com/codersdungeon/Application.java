package com.codersdungeon;

import com.codersdungeon.abstraction.AppInterface;
import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Application implements AppInterface {
    @Autowired
    ItemServiceImpl utenteService;

    public ListFileItemDTO findFilesInDirectory(String percorso){
        System.out.println("Questi sono i file nella cartella " + percorso);
        ListFileItemDTO result = utenteService.findAllFiles(percorso);
        System.out.println(result.items.toString());
        return result;

    }

    public ListFileItemDTO findFilesSubDir(String percorso) {
        System.out.println("Questi sono i file nella cartella " + percorso + " e relative sottocartelle");
        ListFileItemDTO result = utenteService.findAllSubDir(percorso);
        System.out.println(result);
        return result;
    }

    public FileItemDTO copyItem(String filename, String directory, String destination){
        FileItemDTO itemDTO = utenteService.findFile(filename, directory);
        DirectoryDTO dirDto = utenteService.findDirectory(destination);
        System.out.println("Il file " + filename + " è stato copiato in " + destination);

        return utenteService.copyItem(itemDTO, itemDTO.directory, dirDto);
    }

    public void deleteItem(String directory, String filename) {
        FileItemDTO itemDTO = utenteService.findFile(filename, directory);
        utenteService.deleteItem(itemDTO.directory, itemDTO);
        System.out.println("Il file " + filename + " è stato cancellato");
    }
    public ListFileItemDTO backup(String directory,String destination) {
        DirectoryDTO dir = utenteService.findDirectory(directory);
        DirectoryDTO bu = utenteService.findDirectory(destination);
        bu.fileItemDTOS.items.addAll(dir.fileItemDTOS.items);

      System.out.println("Il contenuto di " + directory +" è stato copiato in " + destination);
      return bu.fileItemDTOS;
    }

}
package codersdungeon;

import com.codersdungeon.abstraction.AppInterface;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.dto.ListFileItemDTO;
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
        ListFileItemDTO result = utenteService.findAllFiles(percorso);
        System.out.println(result);
        return result;
    }

    public FileItemDTO copyItem(String filename, String directory, String destination){


        System.out.println("Il file " + filename + " è stato copiato in " + destination);

        return utenteService.copyItem(filename, directory, destination);
    }

    public void deleteItem(String directory, String filename) {
        utenteService.deleteItem(directory, filename);

        System.out.println("Il file " + filename + " è stato cancellato");
    }
    public ListFileItemDTO backup(String destination) {
      System.out.println("Il contenuto è stato copiato in " + destination);
      return utenteService.backup(destination);
    }

}
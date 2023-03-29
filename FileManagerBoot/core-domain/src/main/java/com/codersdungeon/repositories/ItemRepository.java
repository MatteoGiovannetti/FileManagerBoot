package com.codersdungeon.repositories;

import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.entities.Directory;
import com.codersdungeon.entities.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<FileItem, String> {

    @Query ("SELECT i FROM FileItem i WHERE i.filename= :filename AND i.fileType = 'FILE'")
    FileItem findFileByName(@Param("filename") String filename);

    @Query ("SELECT d FROM FileItem d WHERE d.filename = :dirName AND d.fileType = 'FOLDER'" )
    Directory findDirByName(@Param("dirName") String dirName);

    @Query ("SELECT i FROM FileItem i WHERE i.filename= :filename AND i.fileType = 'FILE'")
    FileItem findFileByNameAndFolder(@Param("filename") String filename);

}

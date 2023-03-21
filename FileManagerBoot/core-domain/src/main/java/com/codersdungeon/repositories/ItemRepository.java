package com.codersdungeon.repositories;

import com.codersdungeon.dto.DirectoryDTO;
import com.codersdungeon.dto.FileItemDTO;
import com.codersdungeon.entities.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<FileItem, String> {

    @Query ("SELECT i FROM FileItem i WHERE i.name= :filename AND i.isDirectory is false")
    FileItemDTO findFileByName(@Param("filename") String filename);

    @Query ("SELECT d FROM FileItem d WHERE d.name = :dirName AND d.isDirectory is true" )
    DirectoryDTO findDirByName(@Param("dirName") String dirName);

}

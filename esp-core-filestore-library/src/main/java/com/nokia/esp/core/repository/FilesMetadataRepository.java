package com.nokia.esp.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nokia.esp.core.entities.FileMetadata;

/**
 * 
 * The File MetaData Repository
 *
 */
@Repository
public interface FilesMetadataRepository extends JpaRepository<FileMetadata, Long>{

	/**
	 *  lists the  file metadata available based on the filename
	 * 
	 * @param fileName
	 * 
	 * @return the list of file metadata
	 */
	List<FileMetadata> findByFileName(String fileName);
}

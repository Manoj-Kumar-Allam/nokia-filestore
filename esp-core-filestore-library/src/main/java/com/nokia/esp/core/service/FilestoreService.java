package com.nokia.esp.core.service;

import java.util.List;

import com.nokia.ece.dto.FileMetadataDto;

/**
 * 
 * The File Store Servie
 *
 */
public interface FilestoreService {
	
	/**
	 * Lists all the File Metadata Available in the database
	 * 
	 * @return list of File Metadata
	 */
	List<FileMetadataDto> getAllFilesInfo();
	
	/**
	 * Gives the File Metadata based on the Id 
	 * 
	 * @param id
	 * 
	 * @return the File Metadata
	 */
	FileMetadataDto getFileInfoById(Long id);
}

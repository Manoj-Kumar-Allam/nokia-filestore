package com.nokia.esp.core.service;

import org.springframework.web.multipart.MultipartFile;

import com.nokia.ece.dto.FileMetadataDto;

/**
 * 
 * The File Upload Servie
 *
 */
public interface FilestoreUploadService {
	
	/**
	 * Uploads the Multipart file
	 * 
	 * @param multipartFile
	 * @return the File Metadata of uploaded file
	 */
	FileMetadataDto uploadFile(MultipartFile multipartFile);

}

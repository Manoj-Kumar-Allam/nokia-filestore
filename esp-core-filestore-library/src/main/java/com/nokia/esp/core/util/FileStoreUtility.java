package com.nokia.esp.core.util;

import java.io.File;

import com.nokia.ece.constants.CommonConstants;
import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;

/**
 * 
 * The Filestore Utility Class
 *
 */
public class FileStoreUtility {

	/**
	 * Utility Class, Constructor Cannot be Instantiated
	 */
	private FileStoreUtility() {

	}

	/**
	 * Constructs the File Metadata DTO
	 * 
	 * @param fileMetadata
	 * 
	 * @return File Metadata DTO
	 */
	public static FileMetadataDto constructFileMetadataDTO(FileMetadata fileMetadata) {
		FileMetadataDto fileMetadataDto = new FileMetadataDto();
		fileMetadataDto.setFileId(fileMetadata.getId());
		fileMetadataDto.setFileName(fileMetadata.getFileName());
		fileMetadataDto.setFileRevision(fileMetadata.getFileRevision());
		return fileMetadataDto;
	}

	/**
	 * Constructs the File Metadata Entity
	 * 
	 * @param fileName
	 * @param fileStore
	 * @return the file Metadata
	 */
	public static FileMetadata constructFileMetadataEntity(String fileName, FileStore fileStore) {
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setFileName(fileName);
		fileMetadata.setFileStore(fileStore);
		return fileMetadata;
	}
	
	/**
	 * Joins file name and path. Takes care for file path separator by adding if not available.
	 *
	 * @param fileName the file name
	 * @param localDownloadPath the local download path
	 * @return the string
	 */
	public static String joinFileNameAndPath(String fileName, String localDownloadPath) {
		String[] fileNameArray = fileName.split(CommonConstants.FORWARD_SLASH);
		if (!localDownloadPath.endsWith(File.separator)) {
			return localDownloadPath + File.separator + fileNameArray[fileNameArray.length - 1];
		}
		return localDownloadPath + fileNameArray[fileNameArray.length - 1];
	}

}

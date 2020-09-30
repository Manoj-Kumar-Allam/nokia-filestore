package com.nokia.esp.core.service;

import com.nokia.ece.model.FileDownloadResponse;

/**
 * The File Download Service
 *
 */
public interface FilestoreDownloadService {

	/**
	 * Downloads the file based on the ID 
	 * 
	 * @param id
	 * @return the File Download Response 
	 */
	FileDownloadResponse downloadFile(Long id);
}

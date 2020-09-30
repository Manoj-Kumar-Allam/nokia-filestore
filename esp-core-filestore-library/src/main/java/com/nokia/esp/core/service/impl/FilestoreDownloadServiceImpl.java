package com.nokia.esp.core.service.impl;

import java.io.ByteArrayInputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.ece.model.FileDownloadResponse;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;
import com.nokia.esp.core.repository.FilesMetadataRepository;
import com.nokia.esp.core.service.FilestoreDownloadService;

/**
 * 
 * The File Download Service Implementation
 *
 */
@Service
public class FilestoreDownloadServiceImpl implements FilestoreDownloadService {

	/**
	 * The File Meta Data Repository
	 */
	private final FilesMetadataRepository filesMetadataRepository;

	/**
	 * 
	 * @param filesMetadataRepository
	 */
	public FilestoreDownloadServiceImpl(FilesMetadataRepository filesMetadataRepository) {
		this.filesMetadataRepository = filesMetadataRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nokia.esp.core.service.FilestoreDownloadService#downloadFile(java.lang.
	 * Long)
	 */
	@Override
	@Transactional
	public FileDownloadResponse downloadFile(Long id) {

		Optional<FileMetadata> fileById = this.filesMetadataRepository.findById(id);

		if (fileById.isPresent()) {

			FileMetadata fileMetadata = fileById.get();
			FileStore fileStore = fileMetadata.getFileStore();

			Byte[] file = fileStore.getFile();

			byte[] byteArray = new byte[file.length];

			int i = 0;
			for (Byte b : file) {
				byteArray[i++] = b;
			}
			return FileDownloadResponse.builder().fileName(fileMetadata.getFileName())
					.fileData(new ByteArrayInputStream(byteArray)).build();
		} else {
			throw new FileNotFoundException("File not found for the Id: " + id);
		}
	}

	// to save it in the local workspace

	/*
	 * public FileMetadataDto downloadFile(Long id, String locationToDownload) {
	 * 
	 * Optional<FileMetadata> fileById = this.filesMetadataRepository.findById(id);
	 * 
	 * if (fileById.isPresent()) {
	 * 
	 * FileMetadata fileMetadata = fileById.get(); FileStore fileStore =
	 * fileMetadata.getFileStore();
	 * 
	 * File fileToBeCreated = new File( locationToDownload +
	 * CommonConstants.FORWARD_SLASH + fileMetadata.getFileName()); boolean
	 * isFileCreated = false;
	 * 
	 * try { isFileCreated = fileToBeCreated.createNewFile(); } catch (IOException
	 * e1) { throw new ESPRuntimeException(e1.getMessage()); }
	 * 
	 * if (isFileCreated) { Byte[] file = fileStore.getFile();
	 * 
	 * byte[] byteArray = new byte[file.length];
	 * 
	 * int i = 0; for (Byte b : file) { byteArray[i++] = b; }
	 * 
	 * InputStream is = new ByteArrayInputStream(byteArray);
	 * 
	 * try { IOUtils.copy(is, new FileOutputStream(fileToBeCreated)); } catch
	 * (IOException e) { throw new ESPRuntimeException(e.getMessage()); } return
	 * FileStoreUtility.constructFileMetadataDTO(fileMetadata); } else { throw new
	 * ESPRuntimeException("Unable to to create a new file " +
	 * fileToBeCreated.getAbsolutePath()); }
	 * 
	 * } else { throw new FileNotFoundException("File not found for the Id: " + id);
	 * } }
	 */

}

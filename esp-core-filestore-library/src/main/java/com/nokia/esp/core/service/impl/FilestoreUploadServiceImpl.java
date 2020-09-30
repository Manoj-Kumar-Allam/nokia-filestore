package com.nokia.esp.core.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nokia.ece.constants.CommonConstants;
import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.dto.exception.InvalidFileException;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;
import com.nokia.esp.core.repository.FilesMetadataRepository;
import com.nokia.esp.core.repository.FilestoreRepository;
import com.nokia.esp.core.service.FilestoreUploadService;
import com.nokia.esp.core.util.FileStoreUtility;

/**
 * 
 * The File Store Upload Service Implementation 
 *
 */
@Service
public class FilestoreUploadServiceImpl implements FilestoreUploadService {

	@Value(CommonConstants.ALLOWED_FILE_TYPES)
	private String allowedFileTypes;

	/**
	 * The File Metadata Repository
	 */
	private final FilesMetadataRepository filesMetadataRepository;

	/**
	 * The File Store Repository
	 */
	private final FilestoreRepository filestoreRepository;

	/** Comparator to sort the files by Server Revision */
	Comparator<FileMetadata> fileRivisionComparator = Comparator.comparing(FileMetadata::getFileRevision,
			Comparator.reverseOrder());

	/**
	 * The Filestore Upload ServiceImpl Constructor
	 * 
	 * @param filesMetadataRepository
	 * @param filestoreRepository
	 */
	public FilestoreUploadServiceImpl(FilesMetadataRepository filesMetadataRepository,
			FilestoreRepository filestoreRepository) {
		this.filesMetadataRepository = filesMetadataRepository;
		this.filestoreRepository = filestoreRepository;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nokia.esp.core.service.FilestoreUploadService#uploadFile(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public FileMetadataDto uploadFile(MultipartFile multipartFile) {
		this.validateFile(multipartFile.getOriginalFilename());
		Byte[] byteArray = getByteArray(multipartFile);
		if (byteArray != null) {

			FileStore fileStoreEntity = new FileStore();
			fileStoreEntity.setFile(byteArray);

			FileMetadata fileMetadataEntity = FileStoreUtility.constructFileMetadataEntity(multipartFile.getOriginalFilename(), fileStoreEntity);
			fileMetadataEntity.setFileRevision(getFileRevision(multipartFile.getOriginalFilename()));
			fileStoreEntity.setFileMetadata(fileMetadataEntity);

			FileMetadata savedMetadata = this.filesMetadataRepository.save(fileMetadataEntity);
			this.filestoreRepository.save(fileStoreEntity);

			return FileStoreUtility.constructFileMetadataDTO(savedMetadata);
		} else {
			throw new InvalidFileException("File : " + multipartFile.getOriginalFilename() + " is Invalid or Corrupted");
		}
	}

	/**
	 * Validates the File Extension
	 * 
	 * @param fileName
	 * 
	 * @throws InvalidFileException
	 */
	private void validateFile(String fileName) {
		String fileExtension = fileName.substring(fileName.lastIndexOf(CommonConstants.DOT) + 1);
		List<String> allowedFileTypesList = getAllowedFileTypes();
		if (!allowedFileTypesList.contains(fileExtension.toLowerCase())) {
			throw new InvalidFileException("File Type: " + fileExtension.toLowerCase() + " is not allowed");
		}
	}

	/**
	 * 
	 * @return list of allowed file extensions
	 */
	private List<String> getAllowedFileTypes() {
		return Stream.of(allowedFileTypes.split(",")).map(String::trim).collect(Collectors.toList());
	}

	/**
	 * Converts Multipart file into Byte Array
	 * 
	 * @param multipartFile
	 * 
	 * @return the Byte Array
	 */
	private Byte[] getByteArray(MultipartFile multipartFile) {
		Byte[] bytearray = null;
		try {
			bytearray = new Byte[multipartFile.getBytes().length];
			int i = 0;
			for (byte b : multipartFile.getBytes()) {
				bytearray[i++] = b;
			}
		} catch (IOException e) {

		}
		return bytearray;
	}

	/**
	 * Finds the latest Revision Id
	 * 
	 * @param fileName
	 * 
	 * @return the revision Id
	 */
	private Long getFileRevision(String fileName) {
		List<FileMetadata> fileMetadataList = this.filesMetadataRepository.findByFileName(fileName);
		if (fileMetadataList != null && !fileMetadataList.isEmpty()) {
			Collections.sort(fileMetadataList, fileRivisionComparator);
			return fileMetadataList.get(0).getFileRevision() + 1;
		} else {
			return 1l;
		}
	}

}

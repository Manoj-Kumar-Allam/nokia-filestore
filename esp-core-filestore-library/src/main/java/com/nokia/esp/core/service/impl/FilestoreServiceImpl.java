package com.nokia.esp.core.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.repository.FilesMetadataRepository;
import com.nokia.esp.core.service.FilestoreService;
import com.nokia.esp.core.util.FileStoreUtility;

/*
 * The File Store Service Implementation
 */
@Service
public class FilestoreServiceImpl implements FilestoreService {

	/**
	 * The File Metadata Repository
	 */
	private final FilesMetadataRepository filesMetadataRepository;

	/**
	 * The Filestore Service Impl Constructor
	 * 
	 * @param filesMetadataRepository
	 */
	public FilestoreServiceImpl(FilesMetadataRepository filesMetadataRepository) {
		this.filesMetadataRepository = filesMetadataRepository;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nokia.esp.core.service.FilestoreService#getAllFilesInfo()
	 */
	@Override
	public List<FileMetadataDto> getAllFilesInfo() {
		List<FileMetadata> fileMetadatList = this.filesMetadataRepository.findAll();

		if (fileMetadatList != null && !fileMetadatList.isEmpty()) {
			return fileMetadatList.stream().map(fileMetadata -> FileStoreUtility.constructFileMetadataDTO(fileMetadata))
					.collect(Collectors.toList());
		} else {
			throw new FileNotFoundException("File Store is Empty");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nokia.esp.core.service.FilestoreService#getFileInfoById(java.lang.Long)
	 */
	@Override
	public FileMetadataDto getFileInfoById(Long id) {
		Optional<FileMetadata> fileMetadataById = this.filesMetadataRepository.findById(id);
		if (fileMetadataById.isPresent()) {
			return FileStoreUtility.constructFileMetadataDTO(fileMetadataById.get());
		} else {
			throw new FileNotFoundException("File not found for the Id: " + id);
		}

	}

}

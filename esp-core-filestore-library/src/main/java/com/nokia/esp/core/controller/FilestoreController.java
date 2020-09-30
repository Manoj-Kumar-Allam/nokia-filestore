package com.nokia.esp.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.nokia.ece.constants.CommonConstants;
import com.nokia.ece.constants.ServiceUrls;
import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.model.FileDownloadResponse;
import com.nokia.esp.core.service.FilestoreDownloadService;
import com.nokia.esp.core.service.FilestoreService;
import com.nokia.esp.core.service.FilestoreUploadService;
import com.nokia.esp.core.util.FileStoreUtility;

/**
 * 
 * The File Store Controller
 *
 */
@RestController
@RequestMapping(value = ServiceUrls.FILESTORE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class FilestoreController {

	/**
	 * File Upload Service
	 */
	private final FilestoreUploadService filestoreUploadService;

	/**
	 * File Download Service
	 */
	private final FilestoreDownloadService filestoreDownloadService;

	/**
	 * File Store Service
	 */
	private final FilestoreService filestoreService;

	/**
	 * The File Store Constructor
	 * 
	 * @param filestoreUploadService
	 * @param filestoreDownloadService
	 * @param filestoreService
	 */
	public FilestoreController(FilestoreUploadService filestoreUploadService,
			FilestoreDownloadService filestoreDownloadService, FilestoreService filestoreService) {
		this.filestoreUploadService = filestoreUploadService;
		this.filestoreDownloadService = filestoreDownloadService;
		this.filestoreService = filestoreService;
	}

	/**
	 * The Upload Controller, Uploads the file
	 * 
	 * @param multipartFile
	 * 
	 * @return the File Meta Data DTO
	 */
	@PostMapping(value = ServiceUrls.UPLOAD, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FileMetadataDto uploadFile(@RequestPart("file") MultipartFile multipartFile) {
		return this.filestoreUploadService.uploadFile(multipartFile);
	}

	/**
	 * The Download Controller, Downloads the file into the specified location based
	 * on the ID and locationOfDownload passed in the Request Body
	 * 
	 * @param file ID
	 * @param download Location
	 * 
	 * @return the File Download Response DTO
	 */
	@GetMapping(value = ServiceUrls.DOWNLOAD, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = MediaType.ALL_VALUE)
	public StreamingResponseBody downloadFile(@RequestParam("fileId") Long fileId,
			@RequestParam("downloadLocation") String downloadLocation, HttpServletResponse response) {
		FileDownloadResponse fileDownloadResponse = this.filestoreDownloadService.downloadFile(fileId);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader(CommonConstants.DOWNLOAD_FILE_NAME_WITH_PATH,
				FileStoreUtility.joinFileNameAndPath(fileDownloadResponse.getFileName(), downloadLocation));
		return outputStream -> {
			IOUtils.copy(fileDownloadResponse.getFileData(), outputStream, CommonConstants.DOWNLOAD_FILE_BUFFER_SIZE);
		};
	}

	/**
	 * The Calatog Controller, It will fetch all the available File Meta Data
	 * Information
	 * 
	 * @return the list File Meta Data DTOs available in the file store
	 */
	@GetMapping(value = ServiceUrls.CATALOG, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public List<FileMetadataDto> getAllFilesInfo() {
		return this.filestoreService.getAllFilesInfo();
	}

	/**
	 * The Catalog Controller, It will fetch the File Meta Data Information
	 * according to the ID passed in the request
	 * 
	 * @param id
	 * 
	 * @return the File Meta Data DTO
	 */
	@GetMapping(value = ServiceUrls.CATALOG + "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public FileMetadataDto getFileInfoByName(@PathVariable Long id) {
		return this.filestoreService.getFileInfoById(id);
	}
}

package com.nokia.esp.core.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nokia.ece.constants.CommonConstants;
import com.nokia.ece.constants.ServiceUrls;
import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.ece.model.FileDownloadResponse;
import com.nokia.esp.core.aspect.ExceptionHandlerAdvice;
import com.nokia.esp.core.repository.FilesMetadataRepository;
import com.nokia.esp.core.repository.FilestoreRepository;
import com.nokia.esp.core.service.FilestoreDownloadService;
import com.nokia.esp.core.service.FilestoreService;
import com.nokia.esp.core.service.FilestoreUploadService;
import com.nokia.esp.core.service.impl.FilestoreDownloadServiceImpl;
import com.nokia.esp.core.service.impl.FilestoreServiceImpl;
import com.nokia.esp.core.service.impl.FilestoreUploadServiceImpl;

/**
 * The class <code>FilestoreControllerTest</code> contains tests for the class
 * <code>{@link FilestoreController}</code>.
 *
 * @generatedBy CodePro at 9/27/20 5:03 PM
 * @author manojkumara
 * @version $Revision: 1.0 $
 */
@ExtendWith(MockitoExtension.class)
class FilestoreControllerTest {

	@Mock
	FilestoreUploadService filestoreUploadService;

	@Mock
	FilestoreDownloadService filestoreDownloadService;

	@Mock
	FilestoreService filestoreService;

	@Mock
	HttpServletResponse responce;

	@InjectMocks
	FilestoreController filestoreController;
	

	MockMvc mockMvc;

	ObjectMapper objectMapper;

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@BeforeEach
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.filestoreController).build();
		this.objectMapper = new ObjectMapper();
	}

	/**
	 * Run the
	 * FilestoreController(FilestoreUploadService,FilestoreDownloadService,FilestoreService)
	 * constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testFilestoreController_1() throws Exception {
		FilestoreUploadService filestoreUploadService = new FilestoreUploadServiceImpl((FilesMetadataRepository) null,
				(FilestoreRepository) null);
		FilestoreDownloadService filestoreDownloadService = new FilestoreDownloadServiceImpl(
				(FilesMetadataRepository) null);
		FilestoreService filestoreService = new FilestoreServiceImpl((FilesMetadataRepository) null);

		FilestoreController result = new FilestoreController(filestoreUploadService, filestoreDownloadService,
				filestoreService);
		assertNotNull(result);
	}

	/**
	 * Run the FileMetadataDto downloadFile(FileDownloadRequestDTO) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testDownloadFile_1() throws Exception {
		InputStream inputStream = new ByteArrayInputStream("dummyFile".getBytes());
		
		FileDownloadResponse fileDownloadResponse = new FileDownloadResponse();
		fileDownloadResponse.setFileData(inputStream);
		fileDownloadResponse.setFileName("dummyFilename");
		
		Mockito.when(this.filestoreDownloadService.downloadFile(Mockito.anyLong())).thenReturn(fileDownloadResponse);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.DOWNLOAD)
				.param("fileId", "1").param("downloadLocation", "dummyLocation"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Run the FileMetadataDto downloadFile(FileDownloadRequestDTO) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testDownloadFile_File_Not_Found_Case_2() throws Exception {
		FileMetadataDto response = new FileMetadataDto();
		response.setFileName("dummyFile");

		Mockito.when(this.filestoreDownloadService.downloadFile(Mockito.anyLong()))
				.thenThrow(FileNotFoundException.class);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.filestoreController)
				.setControllerAdvice(new ExceptionHandlerAdvice()).build();

		mockMvc.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.DOWNLOAD).param("fileId", "1")
				.param("downloadLocation", "dummyLocation")).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Run the FileMetadataDto downloadFile(FileDownloadRequestDTO) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testDownloadFile_3() throws Exception {
		InputStream inputStream = new ByteArrayInputStream("dummyFile".getBytes());
		
		FileDownloadResponse fileDownloadResponse = new FileDownloadResponse();
		fileDownloadResponse.setFileData(inputStream);
		fileDownloadResponse.setFileName("dummyFilename");
		
		Mockito.when(this.filestoreDownloadService.downloadFile(Mockito.anyLong())).thenReturn(fileDownloadResponse);

		MvcResult andReturn = this.mockMvc
				.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.DOWNLOAD).param("fileId", "1")
						.param("downloadLocation", "dummyLocation"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		List<String> contentAsString = andReturn.getResponse().getHeaders(CommonConstants.DOWNLOAD_FILE_NAME_WITH_PATH);
		assertEquals("dummyLocation\\dummyFilename", contentAsString.get(0));

	}

	/**
	 * Run the List<FileMetadataDto> getAllFilesInfo() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */

	@Test
	void testGetAllFilesInfo_1() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.CATALOG)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	/**
	 * Run the List<FileMetadataDto> getAllFilesInfo() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */

	@Test
	void testGetAllFilesInfo_2() throws Exception {

		List<FileMetadataDto> result = new ArrayList<>();

		FileMetadataDto dto = new FileMetadataDto();
		dto.setFileName("dummyFile");

		result.add(dto);

		Mockito.when(this.filestoreService.getAllFilesInfo()).thenReturn(result);

		MvcResult andReturn = this.mockMvc
				.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.CATALOG)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();

		assertEquals(this.objectMapper.writeValueAsString(result), contentAsString);

	}

	/**
	 * Run the FileMetadataDto getFileInfoById(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */

	@Test
	void testGetFileInfoById_1() throws Exception {

		FileMetadataDto result = new FileMetadataDto();
		result.setFileName("dummyFile");
		Mockito.when(this.filestoreService.getFileInfoById(Mockito.anyLong())).thenReturn(result);

		MvcResult andReturn = this.mockMvc
				.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.CATALOG + "/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();

		assertEquals(this.objectMapper.writeValueAsString(result), contentAsString);
	}

	@Test
	void testGetFileInfoById_File_Not_Found_Case() throws Exception {

		Mockito.when(this.filestoreService.getFileInfoById(Mockito.anyLong())).thenThrow(FileNotFoundException.class);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(this.filestoreController)
				.setControllerAdvice(new ExceptionHandlerAdvice()).build();

		mockMvc.perform(MockMvcRequestBuilders.get(ServiceUrls.FILESTORE + ServiceUrls.CATALOG + "/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	/**
	 * Run the FileMetadataDto uploadFile(MultipartFile) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	public void testUploadFile_1() throws Exception {

		FileMetadataDto dto = new FileMetadataDto();
		dto.setFileName("dummyFile");
		Mockito.when(this.filestoreUploadService.uploadFile(Mockito.any())).thenReturn(dto);

		MockMultipartFile multiprtFile = new MockMultipartFile("file", "uploadingfile.tar", "text/plain",
				"uploadingfile.tar".getBytes());

		MvcResult andReturn = mockMvc.perform(multipart(ServiceUrls.FILESTORE + ServiceUrls.UPLOAD).file(multiprtFile))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		String contentAsString = andReturn.getResponse().getContentAsString();

		assertEquals(this.objectMapper.writeValueAsString(dto), contentAsString);

		Mockito.verify(this.filestoreUploadService, Mockito.times(1)).uploadFile(Mockito.any());
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@AfterEach
	public void tearDown() throws Exception {
	}

}
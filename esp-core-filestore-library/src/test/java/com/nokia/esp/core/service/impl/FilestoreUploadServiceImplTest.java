package com.nokia.esp.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;

import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.dto.exception.InvalidFileException;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;
import com.nokia.esp.core.repository.FilesMetadataRepository;
import com.nokia.esp.core.repository.FilestoreRepository;

/**
 * The class <code>FilestoreUploadServiceImplTest</code> contains tests for the
 * class <code>{@link FilestoreUploadServiceImpl}</code>.
 *
 * @generatedBy CodePro at 9/27/20 5:04 PM
 * @author manojkumara
 * @version $Revision: 1.0 $
 */
@ExtendWith(MockitoExtension.class)
class FilestoreUploadServiceImplTest {

	@Mock
	FilesMetadataRepository filesMetadataRepository;

	@Mock
	FilestoreRepository filestoreRepository;

	@InjectMocks
	FilestoreUploadServiceImpl filestoreUploadServiceImpl;

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@BeforeEach
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(filestoreUploadServiceImpl, "allowedFileTypes", "tar,txt");
	}

	/**
	 * Run the
	 * FilestoreUploadServiceImpl(FilesMetadataRepository,FilestoreRepository)
	 * constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@Test
	void testFilestoreUploadServiceImpl_1() throws Exception {
		FilesMetadataRepository filesMetadataRepository = null;
		FilestoreRepository filestoreRepository = null;

		FilestoreUploadServiceImpl result = new FilestoreUploadServiceImpl(filesMetadataRepository,
				filestoreRepository);

		assertNotNull(result);
	}

	/**
	 * Run the FileMetadataDto uploadFile(MultipartFile) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@Test
	void testUploadFile_1() throws Exception {

		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setFileName("dummyFile");
		MockMultipartFile multiprtFile = new MockMultipartFile("file", "uploadingfile.tar", "text/plain",
				"uploadingfile.tar".getBytes());

		Mockito.when(this.filesMetadataRepository.save(Mockito.any(FileMetadata.class))).thenReturn(fileMetadata);
		Mockito.when(this.filestoreRepository.save(Mockito.any(FileStore.class))).thenReturn(new FileStore());

		FileMetadataDto uploadFile = this.filestoreUploadServiceImpl.uploadFile(multiprtFile);
		assertEquals(fileMetadata.getFileName(), uploadFile.getFileName());
	}

	@Test
	void testUploadFile_2() throws Exception {

		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setFileName("dummyFile");

		MockMultipartFile multiprtFile = new MockMultipartFile("file", "uploadingfile.jar", "text/plain",
				"uploadingfile.jar".getBytes());

		Exception exception = assertThrows(InvalidFileException.class, () -> {
			this.filestoreUploadServiceImpl.uploadFile(multiprtFile);
		});
		String expectedMessage = "File Type: jar is not allowed";
		assertEquals(expectedMessage, exception.getMessage());
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@AfterEach
	public void tearDown() throws Exception {
	}

}
package com.nokia.esp.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.ece.model.FileDownloadResponse;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;
import com.nokia.esp.core.repository.FilesMetadataRepository;

/**
 * The class <code>FilestoreDownloadServiceImplTest</code> contains tests for
 * the class <code>{@link FilestoreDownloadServiceImpl}</code>.
 *
 * @generatedBy CodePro at 9/27/20 5:03 PM
 * @author manojkumara
 * @version $Revision: 1.0 $
 */
@ExtendWith(MockitoExtension.class)
class FilestoreDownloadServiceImplTest {

	@Mock
	FilesMetadataRepository filesMetadataRepository;

	@InjectMocks
	FilestoreDownloadServiceImpl filestoreDownloadServiceImpl;

	Byte[] byteArray;

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockMultipartFile multiprtFile = new MockMultipartFile("file", "uploadingfile.jar", "text/plain",
				"uploadingfile.jar".getBytes());
		byteArray = getByteArray(multiprtFile);
	}

	/**
	 * Run the FilestoreDownloadServiceImpl(FilesMetadataRepository) constructor
	 * test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testFilestoreDownloadServiceImpl_1() throws Exception {
		FilesMetadataRepository filesMetadataRepository = null;

		FilestoreDownloadServiceImpl result = new FilestoreDownloadServiceImpl(filesMetadataRepository);

		assertNotNull(result);
	}

	/**
	 * Run the FileMetadataDto downloadFile(Long,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testDownloadFile_1() throws Exception {
		Long id = new Long(1L);
		FileMetadata fileMetadata = null;

		Mockito.when(this.filesMetadataRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.ofNullable(fileMetadata));

		Exception allFilesInfo = assertThrows(FileNotFoundException.class, () -> {
			this.filestoreDownloadServiceImpl.downloadFile(id);
		});

		String errorMessage = "File not found for the Id: " + id;

		assertTrue(allFilesInfo.getMessage().contains(errorMessage));
	}

	/**
	 * Run the FileMetadataDto downloadFile(Long,String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testDownloadFile_2() throws Exception {
		Long id = new Long(1L);

		FileStore store = new FileStore();
		store.setFile(byteArray);
		store.setId(id);
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setFileStore(store);
		fileMetadata.setFileName("duumyFile.txt");
		fileMetadata.setId(id);
		fileMetadata.setFileRevision(1l);

		Mockito.when(this.filesMetadataRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fileMetadata));

		FileDownloadResponse downloadFile = this.filestoreDownloadServiceImpl.downloadFile(id);

		assertNotNull(downloadFile);
		assertEquals(fileMetadata.getFileName(), downloadFile.getFileName());
	}

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
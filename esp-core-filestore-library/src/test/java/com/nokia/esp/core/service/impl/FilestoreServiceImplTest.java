package com.nokia.esp.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.ece.dto.exception.FileNotFoundException;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.repository.FilesMetadataRepository;

/**
 * The class <code>FilestoreServiceImplTest</code> contains tests for the class
 * <code>{@link FilestoreServiceImpl}</code>.
 *
 * @generatedBy CodePro at 9/27/20 5:03 PM
 * @author manojkumara
 * @version $Revision: 1.0 $
 */
@ExtendWith(MockitoExtension.class)
class FilestoreServiceImplTest {

	@Mock
	FilesMetadataRepository filesMetadataRepository;

	@InjectMocks
	FilestoreServiceImpl filestoreServiceImpl;

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * Run the FilestoreServiceImpl(FilesMetadataRepository) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testFilestoreServiceImpl_1() throws Exception {
		FilesMetadataRepository filesMetadataRepository = null;

		FilestoreServiceImpl result = new FilestoreServiceImpl(filesMetadataRepository);

		assertNotNull(result);
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

		List<FileMetadata> result = new ArrayList<>();
		FileMetadata dto = new FileMetadata();
		dto.setFileName("dummyFile");

		result.add(dto);

		Mockito.when(this.filesMetadataRepository.findAll()).thenReturn(result);
		List<FileMetadataDto> allFilesInfo = this.filestoreServiceImpl.getAllFilesInfo();
		assertNotNull(allFilesInfo);
		assertEquals(1, allFilesInfo.size());
		assertEquals("dummyFile", allFilesInfo.get(0).getFileName());
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
		List<FileMetadata> result = new ArrayList<>();

		Mockito.when(this.filesMetadataRepository.findAll()).thenReturn(result);

		Exception allFilesInfo = assertThrows(FileNotFoundException.class, () -> {
			this.filestoreServiceImpl.getAllFilesInfo();
		});

		assertTrue(allFilesInfo.getMessage().contains("File Store is Empty"));

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
		Long id = new Long(1L);

		Mockito.when(this.filesMetadataRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.of(new FileMetadata()));

		FileMetadataDto result = this.filestoreServiceImpl.getFileInfoById(id);

		assertNotNull(result);
	}

	/**
	 * Run the FileMetadataDto getFileInfoById(Long) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:03 PM
	 */
	@Test
	void testGetFileInfoById_2() throws Exception {
		Long id = new Long(1L);
		FileMetadata fileMetadata = null;

		Mockito.when(this.filesMetadataRepository.findById(Mockito.anyLong()))
				.thenReturn(Optional.ofNullable(fileMetadata));

		Exception allFilesInfo = assertThrows(FileNotFoundException.class, () -> {
			this.filestoreServiceImpl.getFileInfoById(id);
		});

		String errorMessage = "File not found for the Id: " + id;

		assertTrue(allFilesInfo.getMessage().contains(errorMessage));
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
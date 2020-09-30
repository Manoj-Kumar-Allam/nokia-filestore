package com.nokia.esp.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nokia.ece.dto.FileMetadataDto;
import com.nokia.esp.core.entities.FileMetadata;
import com.nokia.esp.core.entities.FileStore;

/**
 * The class <code>FileStoreUtilityTest</code> contains tests for the class
 * <code>{@link FileStoreUtility}</code>.
 *
 * @generatedBy CodePro at 9/27/20 5:04 PM
 * @author manojkumara
 * @version $Revision: 1.0 $
 */
class FileStoreUtilityTest {

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Run the FileMetadataDto constructFileMetadataDTO(FileMetadata) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@Test
	void testConstructFileMetadataDTO_1() throws Exception {
		FileMetadata fileMetadata = new FileMetadata();
		fileMetadata.setId(new Long(1L));
		fileMetadata.setFileRevision(new Long(1L));
		fileMetadata.setFileName("");

		FileMetadataDto result = FileStoreUtility.constructFileMetadataDTO(fileMetadata);

		assertNotNull(result);
		assertEquals("FileMetadataDto(fileId=1, fileName=, fileRevision=1)", result.toString());
		assertEquals("", result.getFileName());
		assertEquals(new Long(1L), result.getFileRevision());
		assertEquals(new Long(1L), result.getFileId());
	}

	/**
	 * Run the FileMetadata constructFileMetadataEntity(String,FileStore) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/27/20 5:04 PM
	 */
	@Test
	void testConstructFileMetadataEntity_1() throws Exception {
		String fileName = "dummyFileName";
		FileStore fileStore = new FileStore();

		FileMetadata result = FileStoreUtility.constructFileMetadataEntity(fileName, fileStore);

		assertNotNull(result);
		assertEquals(null, result.getId());
		assertEquals("dummyFileName", result.getFileName());
		assertEquals(null, result.getFileRevision());
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
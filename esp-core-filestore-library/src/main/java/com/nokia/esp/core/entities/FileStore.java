package com.nokia.esp.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * 
 * The File Store Entity
 *
 */
@Entity
public class FileStore {

	/** The Id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The File  */
	@Lob
	private Byte[] file;

	/** The File Metadata Association */
	@OneToOne
	private FileMetadata fileMetadata;

	public FileStore() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte[] getFile() {
		return file;
	}

	public void setFile(Byte[] file) {
		this.file = file;
	}

	public FileMetadata getFileMetadata() {
		return fileMetadata;
	}

	public void setFileMetadata(FileMetadata fileMetadata) {
		this.fileMetadata = fileMetadata;
	}

}
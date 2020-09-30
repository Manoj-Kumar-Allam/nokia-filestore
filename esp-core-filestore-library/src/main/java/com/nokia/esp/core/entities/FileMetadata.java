package com.nokia.esp.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * 
 * The Entity File Metadata
 *
 */
@Entity
public class FileMetadata {

	/** The Id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The File Name */
	private String fileName;

	/** The File Revision */
	private Long fileRevision;

	/** The File Store Association */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private FileStore fileStore;

	public FileMetadata() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileRevision() {
		return fileRevision;
	}

	public void setFileRevision(Long fileRevision) {
		this.fileRevision = fileRevision;
	}

	public FileStore getFileStore() {
		return fileStore;
	}

	public void setFileStore(FileStore fileStore) {
		this.fileStore = fileStore;
	}

}

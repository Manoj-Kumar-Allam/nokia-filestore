package com.nokia.ece.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileMetadataDto {

	private Long fileId;

	private String fileName;

	private Long fileRevision;
	
}

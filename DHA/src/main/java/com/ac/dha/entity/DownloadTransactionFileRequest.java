package com.ac.dha.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_eclaim_downTrxFileReq")
public class DownloadTransactionFileRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String login;

	@Column(length = 50)
	private String pwd;

	private String fileID;

	private LocalDateTime requestedAt;

	private String responseStatus;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String fileName;

	@Lob
	@Column(columnDefinition = "TEXT")
	private byte[] file;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String ErrorMessage;

}

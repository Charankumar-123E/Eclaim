package com.ac.dha.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_eclaim_setTrxDwRq")
public class SetTransactionDownloadedRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String fileID;
	
	private LocalDateTime requestedAt;
	
	private String responseStatus;
	
	private String errorMessage;
	
	private  Integer setTransactionDownloadedResult;
	
	
	

}

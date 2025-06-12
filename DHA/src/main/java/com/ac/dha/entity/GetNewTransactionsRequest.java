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
@Table(name = "tbl_eclaim_getNewTrxReq")
public class GetNewTransactionsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;
	
	private LocalDateTime requestedAt;
	
	private Integer result;
	
	private String XmlTransactions;
	
	private String responseStatus;

	private String errorMessage;
	

	

	

}

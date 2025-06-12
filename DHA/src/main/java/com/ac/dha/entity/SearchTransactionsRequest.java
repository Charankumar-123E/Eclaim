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
@Table(name = "tbl_eclaim_searchTrxReq")
public class SearchTransactionsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String direction;

	private String callerLicense;

	private String clinicianLicense;

	private String memberID;

	private String eRxReferenceNo;

	private String transactionStatus;

	private LocalDateTime transactionFromDate;

	private LocalDateTime transactionToDate;

	private int minRecordCount;

	private int maxRecordCount;
	
	private LocalDateTime RequestedAt;
	
	private String responseStatus;
	
	private String foundTransactions;
	
	private String errorMessage;
	
	private Integer searchTransactionResult;
	
	
	
	public SearchTransactionsRequest() {}



	



	

	

}

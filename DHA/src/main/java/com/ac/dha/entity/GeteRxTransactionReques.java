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
@Table(name = "tbl_eclaim_GeteRxTrxReq")
public class GeteRxTransactionReques {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String login;

	private String pwd;

	private String memberID;
	
	private Integer eRxReferenceNo;
	
	private LocalDateTime RequestedAt;
	
	private Integer ErxTransactionResult;
	
	private String xmlTransactions;
	
	private String responseStatus;
	
	private String errorMessage;



	



	

	

}

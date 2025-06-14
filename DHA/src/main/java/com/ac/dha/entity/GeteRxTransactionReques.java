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
@Table(name = "tbl_eclaim_GeteRxTrxReq")
public class GeteRxTransactionReques {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String login;

	@Column(length = 50)
	private String pwd;

	@Column(length = 50)
	private String memberID;

	private Integer eRxReferenceNo;

	private LocalDateTime RequestedAt;

	private Integer ErxTransactionResult;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String xmlTransactions;

	@Column(length = 20)
	private String responseStatus;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String errorMessage;

}

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
@Table(name = "tbl_eclaim_getNewTrxReq")
public class GetNewTransactionsRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String login;

	@Column(length = 100)
	private String pwd;

	private LocalDateTime requestedAt;

	private Integer result;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String xmlTransactions; // Changed to lowercase for convention

	@Column(length = 20)
	private String responseStatus;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String errorMessage;
}
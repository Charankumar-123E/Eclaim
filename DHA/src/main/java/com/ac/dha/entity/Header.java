package com.ac.dha.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_eclaim_header")
@Data
public class Header {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_on")
	// @EpochTime
	private Long createOn;

	@Column(name = "sender_id")
	private String senderID;

	@Column(name = "receiver_id")
	private String receiverID;

	@Column(name = "transaction_date")
	private String transactionDate;

	@Column(name = "record_count")
	private int recordCount;

	@Column(name = "disposition_flag")
	private String dispositionFlag;

}
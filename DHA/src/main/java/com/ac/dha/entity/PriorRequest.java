package com.ac.dha.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
// @EntityListeners({ CustomUniqueCodeListener.class, EpochTimeProcessor.class, CustomColumnProcessor.class })
@Table(name = "tbl_eclaim_prior_requests")
public class PriorRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	public String uniqId;

	@Column(name = "create_by")
	public String createBy;

	@Column(name = "create_on")
	// @EpochTime
	public Long createOn;

	@Column(name = "patient_uhid")
	private String patientUhid;

	@Column(name = "patient_no")
	private String patientNo;

	@Column(name = "header_uniq_id")
	private String headerUniqId;

	@Column(name = "authorization_uniq_id")
	private String authorizationUniqId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "header_id")
	private Header header;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "authorization_id")
	private Authorization authorization;

}

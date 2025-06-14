package com.ac.dha.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_eclaim_observation")
public class Observation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	private String type;

	private String code;

	private String value;

	@Column(name = "value_type")
	private String valueType;

	@ManyToOne
	@JoinColumn(name = "activity_id")
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "authorization_id")
	private Authorization authorizationId;

}
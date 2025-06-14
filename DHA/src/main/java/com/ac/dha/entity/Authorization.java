package com.ac.dha.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"activities", "diagnoses"})
@Table(name = "tbl_eclaim_auth")
public class Authorization {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String type;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

	@Column(name = "authorization_id")
	private String authorizationId;

	@Column(name = "patient_member_id")
	private String patientMemberID;

	@Column(name = "payer_id")
	private String payerID;

	@Column(name = "emirates_id_number")
	private String emiratesIDNumber;

	@Column(name = "date_ordered")
	private String dateOrdered;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authorization_id")
	private List<Diagnosis> diagnoses;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "authorization_id")
	private List<Activity> activities;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "authorization_id")
//	private List<Observation> observations;

	

}
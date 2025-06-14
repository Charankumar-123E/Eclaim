package com.ac.dha.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"authorization", "observations"})
@Table(name = "tbl_eclaim_activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	// @CustomUniqueCode(entityType = "clinicalEditHistory", prefix = "ECPR",
	// numberWidth = 10)
	private String uniqId;

//	@Column(name = "activity_id")
//	private String activityId;

	private String start;

	private String type;

	private String code;

	private int quantity;

	private double net;

	private String clinician;

	@ManyToOne
	@JoinColumn(name = "authorization_id")
	private Authorization authorization;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "activity_id", referencedColumnName = "id")
	private List<Observation> observations;

}
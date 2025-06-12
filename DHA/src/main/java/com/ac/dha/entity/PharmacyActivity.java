package com.ac.dha.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "tbl_eclaim_pharmacy_pharmacyActivity")
@Entity
@Data
public class PharmacyActivity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniq_id;

	@Column(name = "pharmacyActivity_id")
	private String PhActId;

	private String start;

	private String type;

	private String code;

	private double quantity;

	private int duration;

	private int refills;

	private String routOfAdmin;

	private String instructions;

	@OneToOne
	private Frequency frequency;

	@ManyToOne
	@JoinColumn(name = "prescription_id")
	private Prescription prescription;
}

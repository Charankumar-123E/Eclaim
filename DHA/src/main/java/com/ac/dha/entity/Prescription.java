package com.ac.dha.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "tbl_eclaim_pharmacy_prescription")
@Data
@Entity
public class Prescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Long uniq_id;

	@Column(name = "prescription_id")
    private String PresId;

    private String type;

    private String payerID;

    private String clinician;

    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    private Encounter encounter;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PharmacyDiagnosis> pharmDiagnosis;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PharmacyActivity> pharmActivity;
    
    
    
}

package com.ac.dha.DTO.request;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Prescription")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PharamacyPrescriptionDTO {

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "PayerID")
	private String payerID;

	@XmlElement(name = "Clinician")
	private String clinician;

	@XmlElement(name = "Patient")
	private PatientDTO patient;

	@XmlElement(name = "Encounter")
	private EncounterDTO encounter;

	@XmlElement(name = "Diagnosis")
	private List<PharamacyDiagnosisDTO> pharmDiagnosis;

	@XmlElement(name = "Activity")
	private List<PharamacyActivityDTO> pharmActivity;

}
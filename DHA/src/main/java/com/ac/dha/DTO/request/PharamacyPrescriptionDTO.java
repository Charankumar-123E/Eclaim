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

	@XmlElement(name = "pharamacyPrescription_id")
	private String presId;

	@XmlElement(name = "type")
	private String type;

	@XmlElement(name = "payerID")
	private String payerID;

	@XmlElement(name = "clinician")
	private String clinician;

	@XmlElement(name = "patient")
	private PatientDTO patient;

	@XmlElement(name = "encounter")
	private EncounterDTO encounter;

	@XmlElement(name = "pharmDiagnosis")
	private List<PharamacyDiagnosisDTO> pharmDiagnosis;

	@XmlElement(name = "pharmActivity")
	private List<PharamacyActivityDTO> pharmActivity;

}
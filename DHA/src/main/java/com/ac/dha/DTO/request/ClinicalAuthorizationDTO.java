package com.ac.dha.DTO.request;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class ClinicalAuthorizationDTO {

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "PayerID")
	private String payerID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOrdered")
	private String dateOrdered;

	@XmlElement(name = "Encounter")
	private EncounterDTO encounter;

	@XmlElement(name = "Diagnosis")
	private List<ClinicalDiagnosisDTO> diagnoses;

	@XmlElement(name = "Activity")
	private List<ClinicalActivityDTO> activities;

	@XmlElement(name = "Observation")
	private List<ClinicalObservationDTO> observation;

}

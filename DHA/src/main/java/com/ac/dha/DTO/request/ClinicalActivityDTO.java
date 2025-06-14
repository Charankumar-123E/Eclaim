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
public class ClinicalActivityDTO {

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "Start")
	private String start;

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Quantity")
	private int quantity;

	@XmlElement(name = "Net")
	private double net;

	@XmlElement(name = "Clinician")
	private String clinician;

	@XmlElement(name = "Observation")
	private List<ClinicalObservationDTO> observations;
	
	
}

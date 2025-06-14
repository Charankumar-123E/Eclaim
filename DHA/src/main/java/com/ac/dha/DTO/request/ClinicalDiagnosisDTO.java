package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class ClinicalDiagnosisDTO {

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	

}
package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ClinicalObservationDTO {

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Value")
	private String value;

	@XmlElement(name = "ValueType")
	private String valueType;

}

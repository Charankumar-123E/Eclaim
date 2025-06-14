package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClinicalErxRequestDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private ClinicalAuthorizationDTO authorization;
}

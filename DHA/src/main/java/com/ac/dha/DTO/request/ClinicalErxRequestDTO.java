package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClinicalErxRequestDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Authorization")
	private ClinicalAuthorizationDTO authorization;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public ClinicalAuthorizationDTO getAuthorization() {
		return authorization;
	}

	public void setAuthorization(ClinicalAuthorizationDTO authorization) {
		this.authorization = authorization;
	}

}

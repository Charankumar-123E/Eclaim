package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "UploadERxRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxPharmacyRequestDTO {

	@XmlElement(name = "FacilityLogin")
	private String facilityLogin;

	@XmlElement(name = "FacilityPwd")
	private String facilityPwd;

	@XmlElement(name = "ClinicianLogin")
	private String clinicianLogin;

	@XmlElement(name = "ClinicianPwd")
	private String clinicianPwd;

	@XmlElement(name = "FileContent")
	private byte[] fileContent;

	@XmlElement(name = "FileName")
	private String fileName;

}

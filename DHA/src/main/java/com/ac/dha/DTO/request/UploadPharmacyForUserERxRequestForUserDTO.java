package com.ac.dha.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadPharmacyForUserERxRequestForUserDTO {

	@JsonProperty("facilityLogin")
    private String facilityLogin;

    @JsonProperty("facilityPwd")
    private String facilityPwd;

    @JsonProperty("clinicianLogin")
    private String clinicianLogin;

    @JsonProperty("clinicianPwd")
    private String clinicianPwd;

	@XmlElement(name = "FileName")
	private String fileName;
	
	private PharamacyErxDTO priorRequest;


}

package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadERxAuthorizationForUserDTO {

	@XmlElement(name = "PayerLogin")
	private String payerLogin;

	@XmlElement(name = "PayerPwd")
	private String payerPwd;

	@XmlElement(name = "FileName")
	private String fileName;

	private ClinicalErxRequestDTO priorRequest;

	public ClinicalErxRequestDTO getPriorRequest() {
		return priorRequest;
	}

	public void setPriorRequest(ClinicalErxRequestDTO priorRequest) {
		this.priorRequest = priorRequest;
	}

	public String getPayerLogin() {
		return payerLogin;
	}

	public void setPayerLogin(String payerLogin) {
		this.payerLogin = payerLogin;
	}

	public String getPayerPwd() {
		return payerPwd;
	}

	public void setPayerPwd(String payerPwd) {
		this.payerPwd = payerPwd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

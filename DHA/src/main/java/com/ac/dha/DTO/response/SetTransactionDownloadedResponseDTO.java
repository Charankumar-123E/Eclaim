package com.ac.dha.DTO.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SetTransactionDownloadedResponseDTO")
//@Data
public class SetTransactionDownloadedResponseDTO {
	
	@XmlElement(name = "SetTransactionDownloadedResult")
	private Integer setTransactionDownloadedResult;
	
	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	@XmlElement(name = "UploadERxRequestResult")
	private int uploadERxRequestResult;

	public SetTransactionDownloadedResponseDTO(String errorMessage, int uploadERxRequestResult) {
		super();
		this.errorMessage = errorMessage;
		this.uploadERxRequestResult = uploadERxRequestResult;
	}

	public int getUploadERxRequestResult() {
		return uploadERxRequestResult;
	}

	public void setUploadERxRequestResult(int uploadERxRequestResult) {
		this.uploadERxRequestResult = uploadERxRequestResult;
	}

	public SetTransactionDownloadedResponseDTO() {
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "SetTransactionDownloadedResponseDTO [errorMessage=" + errorMessage + ", uploadERxRequestResult="
				+ uploadERxRequestResult + "]";
	}

	public SetTransactionDownloadedResponseDTO(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

}

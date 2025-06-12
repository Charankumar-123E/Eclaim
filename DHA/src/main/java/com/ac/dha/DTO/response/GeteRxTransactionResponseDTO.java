package com.ac.dha.DTO.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GeteRxTransactionResponseDTO")
//@Data
public class GeteRxTransactionResponseDTO {
	
	@XmlElement(name = "ErxTransactionResult")
    private Integer erxTransactionResult;

	@XmlElement(name = "XmlTransactions")
	private String xmlTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	@XmlElement(name = "UploadERxRequestResult")
	private int uploadERxRequestResult;

	public GeteRxTransactionResponseDTO(String xmlTransactions, String errorMessage, int uploadERxRequestResult) {
		super();
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
		this.uploadERxRequestResult = uploadERxRequestResult;
	}

	public int getUploadERxRequestResult() {
		return uploadERxRequestResult;
	}

	public void setUploadERxRequestResult(int uploadERxRequestResult) {
		this.uploadERxRequestResult = uploadERxRequestResult;
	}

	public GeteRxTransactionResponseDTO() {
	}

	public String getXmlTransactions() {
		return xmlTransactions;
	}

	public void setXmlTransactions(String xmlTransactions) {
		this.xmlTransactions = xmlTransactions;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public GeteRxTransactionResponseDTO(String xmlTransactions, String errorMessage) {
		super();
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GeteRxTransactionResponseDTO [xmlTransactions=" + xmlTransactions + ", errorMessage=" + errorMessage
				+ ", uploadERxRequestResult=" + uploadERxRequestResult + "]";
	}

}

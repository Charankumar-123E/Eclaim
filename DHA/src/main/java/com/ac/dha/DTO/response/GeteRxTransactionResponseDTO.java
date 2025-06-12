package com.ac.dha.DTO.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

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
    
    public GeteRxTransactionResponseDTO() {}

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
	
	

	public Integer getErxTransactionResult() {
		return erxTransactionResult;
	}

	public void setErxTransactionResult(Integer erxTransactionResult) {
		this.erxTransactionResult = erxTransactionResult;
	}

	public GeteRxTransactionResponseDTO(Integer erxTransactionResult, String xmlTransactions, String errorMessage) {
		super();
		this.erxTransactionResult = erxTransactionResult;
		this.xmlTransactions = xmlTransactions;
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "GeteRxTransactionResponseDTO [erxTransactionResult=" + erxTransactionResult + ", xmlTransactions="
				+ xmlTransactions + ", errorMessage=" + errorMessage + "]";
	}

	
	
	
    
    
}

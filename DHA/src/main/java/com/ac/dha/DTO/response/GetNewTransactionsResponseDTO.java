package com.ac.dha.DTO.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetNewTransactionsResponseDTO")
@Data
public class GetNewTransactionsResponseDTO {
	
	@XmlElement(name = "GetNewTransactionsResult")
    private Integer result;

	@XmlElement(name = "XmlTransactions")
	private String xmlTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	

	

}

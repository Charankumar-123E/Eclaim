package com.ac.dha.DTO.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SearchTransactionsResponseDTO")
//@Data
public class SearchTransactionsResponseDTO {
	
	@XmlElement(name = "SearchTransactionsResult")
	private Integer searchTransactionsResult;

	@XmlElement(name = "FoundTransactions")
	private String foundTransactions;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	public Integer getSearchTransactionsResult() {
		return searchTransactionsResult;
	}

	public void setSearchTransactionsResult(Integer searchTransactionsResult) {
		this.searchTransactionsResult = searchTransactionsResult;
	}

	public String getFoundTransactions() {
		return foundTransactions;
	}

	public void setFoundTransactions(String foundTransactions) {
		this.foundTransactions = foundTransactions;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public SearchTransactionsResponseDTO(Integer searchTransactionsResult, String foundTransactions,
			String errorMessage) {
		super();
		this.searchTransactionsResult = searchTransactionsResult;
		this.foundTransactions = foundTransactions;
		this.errorMessage = errorMessage;
	}

	public SearchTransactionsResponseDTO() {
		super();
	}

	@Override
	public String toString() {
		return "SearchTransactionsResponseDTO [searchTransactionsResult=" + searchTransactionsResult
				+ ", foundTransactions=" + foundTransactions + ", errorMessage=" + errorMessage + "]";
	}
	
	

}

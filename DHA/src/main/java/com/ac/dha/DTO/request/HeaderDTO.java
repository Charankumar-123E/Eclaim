package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlRootElement(name = "Header")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class HeaderDTO {

	@XmlElement(name = "SenderID")
	private String senderID;

	@XmlElement(name = "ReceiverID")
	private String receiverID;

	@XmlElement(name = "TransactionDate")
	private String transactionDate;

	@XmlElement(name = "RecordCount")
	private int recordCount;

	@XmlElement(name = "DispositionFlag")
	private String dispositionFlag;

}

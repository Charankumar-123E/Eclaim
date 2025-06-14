package com.ac.dha.DTO.response;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "UploadERxRequestResponseDTO")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UploadERxRequestResponseDTO  {


	@XmlElement(name = "ERxReferenceNo")
	private Integer eRxReferenceNo;

	@XmlElement(name = "status")
	private String status;

	@XmlElement(name = "ErrorMessage")
	private String errorMessage;

	@XmlElement(name = "ErrorReport")
	private byte[] errorReport;

	
}

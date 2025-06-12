package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PatientDTO {

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOfBirth")
	private String dateOfBirth;

	@XmlElement(name = "Weight")
	private double weight;

}
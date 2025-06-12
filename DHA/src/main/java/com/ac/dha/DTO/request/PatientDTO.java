package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PatientDTO {

	@XmlElement(name = "MemberID")
	private String memberID;

	@XmlElement(name = "EmiratesIDNumber")
	private String emiratesIDNumber;

	@XmlElement(name = "DateOfBirth")
	private String dateOfBirth;

	@XmlElement(name = "Weight")
	private double weight;

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getEmiratesIDNumber() {
		return emiratesIDNumber;
	}

	public void setEmiratesIDNumber(String emiratesIDNumber) {
		this.emiratesIDNumber = emiratesIDNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "PatientDTO [memberID=" + memberID + ", emiratesIDNumber=" + emiratesIDNumber + ", dateOfBirth="
				+ dateOfBirth + ", weight=" + weight + "]";
	}

	public PatientDTO() {
		super();
	}

	public PatientDTO(String memberID, String emiratesIDNumber, String dateOfBirth, double weight) {
		super();
		this.memberID = memberID;
		this.emiratesIDNumber = emiratesIDNumber;
		this.dateOfBirth = dateOfBirth;
		this.weight = weight;
	}

}
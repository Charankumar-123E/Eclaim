package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prior.Request")
@XmlAccessorType(XmlAccessType.FIELD)
public class PharmacyErxDTO {

	@XmlElement(name = "Header")
	private HeaderDTO header;

	@XmlElement(name = "Prescription")
	private PrescriptionDTO prescription;

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public PrescriptionDTO getPrescription() {
		return prescription;
	}

	public void setPrescription(PrescriptionDTO prescription) {
		this.prescription = prescription;
	}

	@Override
	public String toString() {
		return "PharmacyErxDTO [header=" + header + ", prescription=" + prescription + "]";
	}

	public PharmacyErxDTO(HeaderDTO header, PrescriptionDTO prescription) {
		super();
		this.header = header;
		this.prescription = prescription;
	}

	public PharmacyErxDTO() {}
}

package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PharamacyActivityDTO {

	@XmlElement(name = "ID")
	private String id;

	@XmlElement(name = "Start")
	private String start;

	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Quantity")
	private double quantity;

	@XmlElement(name = "Duration")
	private int duration;

	@XmlElement(name = "Refills")
	private int refills;

	@XmlElement(name = "RoutOfAdmin")
	private String routOfAdmin;

	@XmlElement(name = "Instructions")
	private String instructions;

	@XmlElement(name = "Frequency")
	private FrequencyDTO frequency;

}

package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PActivityDTO {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRefills() {
		return refills;
	}

	public void setRefills(int refills) {
		this.refills = refills;
	}

	public String getRoutOfAdmin() {
		return routOfAdmin;
	}

	public void setRoutOfAdmin(String routOfAdmin) {
		this.routOfAdmin = routOfAdmin;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public FrequencyDTO getFrequency() {
		return frequency;
	}

	public void setFrequency(FrequencyDTO frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "PharmacyActivityDTO [id=" + id + ", start=" + start + ", type=" + type + ", code=" + code
				+ ", quantity=" + quantity + ", duration=" + duration + ", refills=" + refills + ", routOfAdmin="
				+ routOfAdmin + ", instructions=" + instructions + ", frequency=" + frequency + "]";
	}

	public PActivityDTO(String id, String start, String type, String code, double quantity, int duration, int refills,
			String routOfAdmin, String instructions, FrequencyDTO frequency) {
		super();
		this.id = id;
		this.start = start;
		this.type = type;
		this.code = code;
		this.quantity = quantity;
		this.duration = duration;
		this.refills = refills;
		this.routOfAdmin = routOfAdmin;
		this.instructions = instructions;
		this.frequency = frequency;
	}

	public PActivityDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}

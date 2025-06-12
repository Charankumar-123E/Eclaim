package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class ObservationDTO {

	
	@XmlElement(name = "Type")
	private String type;

	@XmlElement(name = "Code")
	private String code;

	@XmlElement(name = "Value")
	private String value;

	@XmlElement(name = "ValueType")
	private String valueType;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	
	public ObservationDTO(String type, String code, String value, String valueType) {
		super();
		this.type = type;
		this.code = code;
		this.value = value;
		this.valueType = valueType;
	}

	@Override
	public String toString() {
		return "ObservationDTO [type=" + type + ", code=" + code + ", value=" + value + ", valueType=" + valueType
				+ "]";
	}

	public ObservationDTO() {
		super();
	}


	
	

}

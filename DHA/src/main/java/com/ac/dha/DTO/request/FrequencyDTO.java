package com.ac.dha.DTO.request;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class FrequencyDTO {

    @XmlElement(name = "UnitPerFrequency")
    private double unitPerFrequency;

    @XmlElement(name = "FrequencyValue")
    private int frequencyValue;

    @XmlElement(name = "FrequencyType")
    private String frequencyType;
}
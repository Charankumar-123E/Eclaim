package com.ac.dha.utils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ac.dha.DTO.request.EncounterDTO;
import com.ac.dha.DTO.request.FrequencyDTO;
import com.ac.dha.DTO.request.HeaderDTO;
import com.ac.dha.DTO.request.PatientDTO;
import com.ac.dha.DTO.request.PharamacyActivityDTO;
import com.ac.dha.DTO.request.PharamacyDiagnosisDTO;
import com.ac.dha.DTO.request.PharamacyErxDTO;
import com.ac.dha.DTO.request.PharamacyPrescriptionDTO;
import com.ac.dha.entity.Encounter;
import com.ac.dha.entity.Frequency;
import com.ac.dha.entity.Header;
import com.ac.dha.entity.Patient;
import com.ac.dha.entity.PharmacyActivity;
import com.ac.dha.entity.PharmacyDiagnosis;
import com.ac.dha.entity.PharmacyERXRequest;
import com.ac.dha.entity.Prescription;

@Component
public class PharmacyEntityMapper {

	public PharmacyERXRequest toPharmacyERXRequest(PharamacyErxDTO dto) {
		PharmacyERXRequest request = new PharmacyERXRequest();
		request.setHeader(toHeader(dto.getHeader()));
		request.setPrescription(toPrescription(dto.getPrescription()));
		return request;
	}

	private Header toHeader(HeaderDTO dto) {
		Header header = new Header();
		header.setSenderID(dto.getSenderID());
		header.setReceiverID(dto.getReceiverID());
		header.setTransactionDate(dto.getTransactionDate());
		header.setRecordCount(dto.getRecordCount());
		header.setDispositionFlag(dto.getDispositionFlag());
		return header;
	}

	private Prescription toPrescription(PharamacyPrescriptionDTO dto) {
		Prescription prescription = new Prescription();

		prescription.setPresId(dto.getId());
		prescription.setType(dto.getType());
		prescription.setPayerID(dto.getPayerID());
		prescription.setClinician(dto.getClinician());

		// Patient
		if (dto.getPatient() != null) {
			Patient patient = toPatient(dto.getPatient());
			prescription.setPatient(patient);
		}

		// Encounter
		if (dto.getEncounter() != null) {
			Encounter encounter = toEncounter(dto.getEncounter());
			prescription.setEncounter(encounter);
		}

		// Diagnosis
		if (dto.getPharmDiagnosis() != null && !dto.getPharmDiagnosis().isEmpty()) {
			List<PharmacyDiagnosis> diagnoses = dto.getPharmDiagnosis().stream().map(this::toPharmacyDiagnosis)
					.collect(Collectors.toList());

			diagnoses.forEach(d -> d.setPrescription(prescription));
			prescription.setPharmDiagnosis(diagnoses);
		}

		// Activity
		if (dto.getPharmActivity() != null && !dto.getPharmActivity().isEmpty()) {
			List<PharmacyActivity> activities = dto.getPharmActivity().stream().map(this::toPharmacyActivity)
					.collect(Collectors.toList());

			activities.forEach(a -> a.setPrescription(prescription));
			prescription.setPharmActivity(activities);
		}

		return prescription;
	}

	private Encounter toEncounter(EncounterDTO dto) {
		Encounter encounter = new Encounter();
		encounter.setUniqId(UUID.randomUUID().toString());
		encounter.setFacilityID(dto.getFacilityID());
		encounter.setType(dto.getType());
		return encounter;
	}

	private Patient toPatient(PatientDTO dto) {
		Patient patient = new Patient();
		patient.setUniqId(UUID.randomUUID().toString());
		patient.setMemberID(dto.getMemberID());
		patient.setEmiratesIDNumber(dto.getEmiratesIDNumber());
		patient.setDateOfBirth(dto.getDateOfBirth());
		patient.setWeight(dto.getWeight());
		return patient;
	}

	private PharmacyDiagnosis toPharmacyDiagnosis(PharamacyDiagnosisDTO dto) {
		PharmacyDiagnosis diagnosis = new PharmacyDiagnosis();
		diagnosis.setUniq_id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		diagnosis.setType(dto.getType());
		diagnosis.setCode(dto.getCode());
		return diagnosis;
	}

	private PharmacyActivity toPharmacyActivity(PharamacyActivityDTO dto) {
		PharmacyActivity activity = new PharmacyActivity();
		activity.setUniq_id(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
		activity.setPhActId(dto.getId());
		activity.setStart(dto.getStart());
		activity.setType(dto.getType());
		activity.setCode(dto.getCode());
		activity.setQuantity(dto.getQuantity());
		activity.setDuration(dto.getDuration());
		activity.setRefills(dto.getRefills());
		activity.setRoutOfAdmin(dto.getRoutOfAdmin());
		activity.setInstructions(dto.getInstructions());

		if (dto.getFrequency() != null) {
			Frequency frequency = toFrequency(dto.getFrequency());
			activity.setFrequency(frequency);
		}

		return activity;
	}

	private Frequency toFrequency(FrequencyDTO dto) {
		Frequency frequency = new Frequency();
		frequency.setUnitPerFrequency(dto.getUnitPerFrequency());
		frequency.setFrequencyValue(dto.getFrequencyValue());
		frequency.setFrequencyType(dto.getFrequencyType());
		return frequency;

	}
}

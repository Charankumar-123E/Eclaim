package com.ac.dha.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ac.dha.DTO.request.ClinicalActivityDTO;
import com.ac.dha.DTO.request.ClinicalAuthorizationDTO;
import com.ac.dha.DTO.request.ClinicalDiagnosisDTO;
import com.ac.dha.DTO.request.ClinicalErxRequestDTO;
import com.ac.dha.DTO.request.ClinicalObservationDTO;
import com.ac.dha.DTO.request.EncounterDTO;
import com.ac.dha.DTO.request.HeaderDTO;
import com.ac.dha.entity.Activity;
import com.ac.dha.entity.Authorization;
import com.ac.dha.entity.Diagnosis;
import com.ac.dha.entity.Encounter;
import com.ac.dha.entity.Header;
import com.ac.dha.entity.Observation;
import com.ac.dha.entity.PriorRequest;

@Component
public class ClinicalERXEntityMapper {

	public PriorRequest toPriorRequest(ClinicalErxRequestDTO dto) {
		PriorRequest priorRequest = new PriorRequest();
		priorRequest.setHeader(toHeader(dto.getHeader()));
		priorRequest.setAuthorization(toAuthorization(dto.getAuthorization()));
		return priorRequest;
	}

	private Header toHeader(HeaderDTO dto) {
		if (dto == null) {
			return null;
		}
		Header header = new Header();
		header.setSenderID(dto.getSenderID());
		header.setReceiverID(dto.getReceiverID());
		header.setTransactionDate(dto.getTransactionDate());
		header.setRecordCount(dto.getRecordCount());
		header.setDispositionFlag(dto.getDispositionFlag());
		return header;
	}

	private Authorization toAuthorization(ClinicalAuthorizationDTO dto) {
		if (dto == null) {
			return null;
		}
		Authorization authorization = new Authorization();
		authorization.setType(dto.getType());
		authorization.setAuthorizationId(dto.getId());
		authorization.setPatientMemberID(dto.getMemberID());
		authorization.setPayerID(dto.getPayerID());
		authorization.setEmiratesIDNumber(dto.getEmiratesIDNumber());
		authorization.setDateOrdered(dto.getDateOrdered());
//		authorization.setEncounter(toEncounter(dto.getEncounter()));
		Encounter encounter = new Encounter();
		if(encounter != null) {
			authorization.setEncounter(encounter);
		}
		if (dto.getDiagnoses() != null) {
			List<Diagnosis> diagnoses = dto.getDiagnoses().stream().map(this::toDiagnosis).collect(Collectors.toList());
			diagnoses.forEach(diagnosis -> diagnosis.setAuthorization(authorization));
			authorization.setDiagnoses(diagnoses);
		}

		if (dto.getActivities() != null) {
			List<Activity> activities = dto.getActivities().stream().map(this::toActivity).collect(Collectors.toList());
			activities.forEach(activity -> activity.setAuthorization(authorization));
			authorization.setActivities(activities);
		}

		return authorization;
	}

	private Encounter toEncounter(EncounterDTO dto) {
		if (dto == null) {
			return null;
		}
		Encounter encounter = new Encounter();
		encounter.setFacilityID(dto.getFacilityID());
		encounter.setType(dto.getType());
//		encounter.setAuthorization(null);
		return encounter;
	}

	private Diagnosis toDiagnosis(ClinicalDiagnosisDTO dto) {
		if (dto == null) {
			return null;
		}
		Diagnosis diagnosis = new Diagnosis();
		diagnosis.setType(dto.getType());
		diagnosis.setCode(dto.getCode());
		return diagnosis;
	}

	private Activity toActivity(ClinicalActivityDTO dto) {
		if (dto == null)
			return null;
		Activity activity = new Activity();
		activity.setStart(dto.getStart());
		activity.setType(dto.getType());
		activity.setCode(dto.getCode());
		activity.setQuantity(dto.getQuantity());
		activity.setNet(dto.getNet());
		activity.setClinician(dto.getClinician());

		if (dto.getObservations() != null) {
			List<Observation> observations = dto.getObservations().stream().map(this::toObservation)
					.collect(Collectors.toList());
			observations.forEach(observation -> observation.setActivity(activity));
			activity.setObservations(observations);
		}

		return activity;
	}

	private Observation toObservation(ClinicalObservationDTO dto) {
		if (dto == null)
			return null;
		Observation observation = new Observation();
		observation.setType(dto.getType());
		observation.setCode(dto.getCode());
		observation.setValue(dto.getValue());
		observation.setValueType(dto.getValueType());
		return observation;
	}
}
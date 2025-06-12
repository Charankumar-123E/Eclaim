package com.ac.dha.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ac.dha.DTO.request.ErxRequestDTO;
import com.ac.dha.DTO.request.UploadERxRequestDTO;
import com.ac.dha.DTO.request.UploadERxRequestForUserDTO;
import com.ac.dha.DTO.response.UploadERxRequestResponseDTO;
import com.ac.dha.entity.PriorRequest;
import com.ac.dha.entity.UploadERxRequest;
import com.ac.dha.repository.PriorRequestRepository;
import com.ac.dha.repository.UploadERxRequestClinicRepository;
import com.ac.dha.utils.ERXEntityMapper;
import com.ac.dha.utils.XmlUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBException;

@Service
public class EclaimService {

	private final RestTemplate restTemplate = new RestTemplate();
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private PriorRequestRepository priorRequestRepository;

	@Autowired
	private UploadERxRequestClinicRepository uploadERxRequestClinicRepository;

	public static final Logger log = LoggerFactory.getLogger(EclaimService.class);

	@Autowired
	private ERXEntityMapper dtoToEntityMapper;

	public ResponseEntity<String> sendPriorRequestToEclaim(ErxRequestDTO priorRequest) {
		try {
			log.info("Req Format ", priorRequest.getAuthorization());
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);

			System.out.println("XML Payload byte[] length: " + xmlPayload.length);

			String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println(" XML Payload as String:\n ------>" + xmlFormat);

			PriorRequest entity = dtoToEntityMapper.toPriorRequest(priorRequest);
			priorRequestRepository.save(entity);
			log.info("PriorRequest saved to database with ID: {}", entity.getId());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl, requestEntity, String.class);

			log.info("Response Status Code: ", response.getStatusCodeValue());
			log.info("Response Body:\n", response.getBody());

			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());

		} catch (Exception e) {
			log.error("Exception in sendPriorRequestToEclaim: ", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
		try {
			log.info("uploadERxRequest called");

			// Validate required fields
			if (requestFromUser == null || requestFromUser.getPriorRequest() == null
					|| requestFromUser.getFileName() == null) {
				String errorMsg = requestFromUser == null ? "Request body is null"
						: requestFromUser.getPriorRequest() == null ? "priorRequest is null" : "fileName is null";
				return buildErrorResponse(400, errorMsg, "Validation failed");
			}

			// Convert to XML
			byte[] xmlPayload = xmlUtil.convertToXml(requestFromUser.getPriorRequest());
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
			log.debug("XML Payload: {}", xmlString);

			// Create UploadERxRequestDTO for external API
			UploadERxRequestDTO request = new UploadERxRequestDTO();
			request.setFacilityLogin(requestFromUser.getFacilityLogin());
			request.setFacilityPwd(requestFromUser.getFacilityPwd());
			request.setClinicianLogin(requestFromUser.getClinicianLogin());
			request.setClinicianPwd(requestFromUser.getClinicianPwd());
			request.setFileName(requestFromUser.getFileName());
			request.setFileContent(Base64.getEncoder().encode(xmlPayload));

			// Prepare entity
			UploadERxRequest entity = new UploadERxRequest();
			entity.setUniqId(1);
			entity.setFacilityLogin(request.getFacilityLogin());
			entity.setFacilityPwd(request.getFacilityPwd());
			entity.setClinicianLogin(request.getClinicianLogin());
			entity.setClinicianPwd(request.getClinicianPwd());
			entity.setFileName(request.getFileName());
			entity.setFileContent(xmlPayload);

			System.out.println("entity => " + entity);
			// Save to DB
			UploadERxRequest savedEntity = uploadERxRequestClinicRepository.save(entity);
			log.info("Saved UploadERxRequest with ID: {}, eRxReferenceNo: {}", savedEntity.getId(),
					savedEntity.getUniqId());

			// Send to external API
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlUtil.convertToXml(request), headers);

			ResponseEntity<String> apiResponse = restTemplate.postForEntity(eclaimUrl + "/uploadERxRequest",
					requestEntity, String.class);
			log.info("External API status: {}, body: {}", apiResponse.getStatusCodeValue(), apiResponse.getBody());

			// Parse API response
			String generatedERxRefNo = "ERX-" + System.currentTimeMillis();
			savedEntity.seteRxReferenceNo(generatedERxRefNo); // ✅ Set in entity
			uploadERxRequestClinicRepository.save(savedEntity);
			UploadERxRequestResponseDTO responseDTO = new UploadERxRequestResponseDTO();
			responseDTO.seteRxReferenceNo(generatedERxRefNo);
			responseDTO.setStatus("SUCCESS");
			responseDTO.setErrorMessage(null);
			responseDTO.setErrorReport(null);

			System.out.println("geteRxReferenceNo----------->" + responseDTO.geteRxReferenceNo());

			return ResponseEntity.ok(objectMapper.writeValueAsString(responseDTO));

		} catch (JAXBException e) {
			log.error("XML processing error", e);
			return buildErrorResponse(500, "XML Error: " + e.getMessage(), "Failed to process XML");
		} catch (Exception e) {
			log.error("Unexpected error", e);
			return buildErrorResponse(500, "Error: " + e.getMessage(), "Unexpected error occurred");
		}
	}

	private ResponseEntity<String> buildErrorResponse(int statusCode, String message, String report) {
		try {
			UploadERxRequestResponseDTO errorResponse = new UploadERxRequestResponseDTO();
			errorResponse.seteRxReferenceNo(null);
			errorResponse.setErrorMessage(message);
			errorResponse.setErrorReport(report.getBytes(StandardCharsets.UTF_8));
			return ResponseEntity.status(statusCode).body(objectMapper.writeValueAsString(errorResponse));
		} catch (Exception e) {
			log.error("Failed to serialize error response", e);
			return ResponseEntity.status(500).body("Error: Failed to serialize response");
		}
	}
}

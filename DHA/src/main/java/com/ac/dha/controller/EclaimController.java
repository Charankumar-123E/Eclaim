package com.ac.dha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ac.dha.DTO.request.PharamacyErxDTO;
import com.ac.dha.DTO.request.UploadERxRequestForUserDTO;
import com.ac.dha.service.EclaimService;

@RestController
@RequestMapping("/api/eclaim")
public class EclaimController {

	private static final Logger log = LoggerFactory.getLogger(EclaimController.class);

	@Autowired
	private EclaimService eclaimService;

	@PostMapping(value = "/prior-request", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> sendPriorRequest(@RequestBody PharamacyErxDTO priorRequest) {
		log.info("Received prior request: {}", priorRequest);
		try {
			ResponseEntity<String> response = eclaimService.sendPriorRequestToEclaim(priorRequest);
			log.info("Prior request processed successfully with status: {}", response.getStatusCode());
			return response;
		} catch (Exception e) {
			log.error("Error processing prior request", e);
			return ResponseEntity.status(500).body("Error processing prior request: " + e.getMessage());
		}
	}

	@PostMapping(value = "/upload-erx", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> uploadERxRequest(@RequestBody UploadERxRequestForUserDTO request) {
		log.info("Received eRx upload request: {}", request);
		try {
			ResponseEntity<String> response = eclaimService.uploadERxRequest(request);
			log.info("eRx upload request processed successfully with status: {}", response.getStatusCode());
			return response;
		} catch (Exception e) {
			log.error("Error processing eRx upload request", e);
			return ResponseEntity.status(500).body("Error processing eRx upload request: " + e.getMessage());
		}
	}
}
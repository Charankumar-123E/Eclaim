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

import com.ac.dha.DTO.request.ClinicalErxRequestDTO;
import com.ac.dha.DTO.request.DownloadTransactionFileRequestDTO;
import com.ac.dha.DTO.request.GetNewTransactionsRequestDTO;
import com.ac.dha.DTO.request.GeteRxTransactionRequestDTO;
import com.ac.dha.DTO.request.PharamacyErxDTO;
import com.ac.dha.DTO.request.SearchTransactionsRequestDTO;
import com.ac.dha.DTO.request.SetTransactionDownloadedRequestDTO;
import com.ac.dha.DTO.request.UploadERxRequestForUserDTO;
import com.ac.dha.DTO.request.UploadPharmacyForUserERxRequestForUserDTO;
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

	@PostMapping(value = "/clinic-request", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> sendclinicPriorRequest(@RequestBody ClinicalErxRequestDTO priorRequest) {
		log.info("Received prior request: {}", priorRequest);
		try {
			ResponseEntity<String> response = eclaimService.sendclinicPriorRequest(priorRequest);
			log.info("Prior request processed successfully with status: {}", response.getStatusCode());
			return response;
		} catch (Exception e) {
			log.error("Error processing prior request", e);
			return ResponseEntity.status(500).body("Error processing prior request: " + e.getMessage());
		}
	}

	@PostMapping(value = "/uploadERxRequest", consumes = { MediaType.APPLICATION_JSON_VALUE,
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

	@PostMapping(value = "/uploadPharmacyERxRequest", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> uploadPharmacy(@RequestBody UploadPharmacyForUserERxRequestForUserDTO request) {
		log.info("Received eRx upload request: {}", request);
		try {
			ResponseEntity<String> response = eclaimService.uploadPharmacy(request);
			log.info("eRx upload request processed successfully with status: {}", response.getStatusCode());
			return response;
		} catch (Exception e) {
			log.error("Error processing eRx upload request", e);
			return ResponseEntity.status(500).body("Error processing eRx upload request: " + e.getMessage());
		}
	}

	@PostMapping("/GetNewTransactions")
	public ResponseEntity<String> getNewTransactions(@RequestBody GetNewTransactionsRequestDTO dto) {
		return eclaimService.getNewTransactions(dto);
	}

	@PostMapping("/GeteRxTransaction")
	public ResponseEntity<String> geteRxTransaction(@RequestBody GeteRxTransactionRequestDTO dto) {
		return eclaimService.geteRxTransaction(dto);
	}

	@PostMapping("/SearchTransactions")
	public ResponseEntity<String> searchTransactions(@RequestBody SearchTransactionsRequestDTO dto) {
		return eclaimService.searchTransactions(dto);
	}

	@PostMapping("/DownloadTransactionFile")
	public ResponseEntity<String> downloadTransactionFile(@RequestBody DownloadTransactionFileRequestDTO dto) {
		return eclaimService.downloadTransactionFile(dto);
	}

	@PostMapping("/SetTransactionDownloaded")
	public ResponseEntity<String> setTransactionDownloaded(@RequestBody SetTransactionDownloadedRequestDTO dto) {
		return eclaimService.setTransactionDownloaded(dto);
	}
}
package com.ac.dha.service;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ac.dha.DTO.request.ClinicalErxRequestDTO;
import com.ac.dha.DTO.request.DownloadTransactionFileRequestDTO;
import com.ac.dha.DTO.request.GetNewTransactionsRequestDTO;
import com.ac.dha.DTO.request.GeteRxTransactionRequestDTO;
import com.ac.dha.DTO.request.PharamacyErxDTO;
import com.ac.dha.DTO.request.SearchTransactionsRequestDTO;
import com.ac.dha.DTO.request.SetTransactionDownloadedRequestDTO;
import com.ac.dha.DTO.request.UploadERxPharmacyRequestDTO;
import com.ac.dha.DTO.request.UploadERxRequestDTO;
import com.ac.dha.DTO.request.UploadERxRequestForUserDTO;
import com.ac.dha.DTO.request.UploadPharmacyForUserERxRequestForUserDTO;
import com.ac.dha.DTO.response.DownloadTransactionFileResponseDTO;
import com.ac.dha.DTO.response.GetNewTransactionsResponseDTO;
import com.ac.dha.DTO.response.GeteRxTransactionResponseDTO;
import com.ac.dha.DTO.response.PharmacyERXRequestResponseDTO;
import com.ac.dha.DTO.response.SearchTransactionsResponseDTO;
import com.ac.dha.DTO.response.SetTransactionDownloadedResponseDTO;
import com.ac.dha.DTO.response.UploadERxRequestResponseDTO;
import com.ac.dha.entity.DownloadTransactionFileRequest;
import com.ac.dha.entity.GetNewTransactionsRequest;
import com.ac.dha.entity.GeteRxTransactionReques;
import com.ac.dha.entity.PharmacyERXRequest;
import com.ac.dha.entity.PharmacyUploadERX;
import com.ac.dha.entity.PriorRequest;
import com.ac.dha.entity.SearchTransactionsRequest;
import com.ac.dha.entity.SetTransactionDownloadedRequest;
import com.ac.dha.entity.UploadERxRequest;
import com.ac.dha.repository.ClinicalRequestRepository;
import com.ac.dha.repository.DownloadTransactionFileRepository;
import com.ac.dha.repository.GetNewTransactionsRepository;
import com.ac.dha.repository.GeteRxTransactionRequestRepository;
import com.ac.dha.repository.PharmacyRequestRepository;
import com.ac.dha.repository.SearchTransactionsRepository;
import com.ac.dha.repository.SetTransactionDownloadedRepository;
import com.ac.dha.repository.UploadERxRequestClinicRepository;
import com.ac.dha.repository.UploadERxRequestPharmacyRepository;
import com.ac.dha.utils.ClinicalERXEntityMapper;
import com.ac.dha.utils.Generator;
import com.ac.dha.utils.PharmacyEntityMapper;
import com.ac.dha.utils.XmlUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@Service
public class EclaimService {

	@Value("${dha.base-url}")
	private String baseUrl;

//	@Value("${dha.base-url}")
//	private String erxUrl;
//
//	@Value("${eclaim.endpoint.url}")
//	private String eclaimUrl;

	@Autowired
	public Generator generator;

	private final RestTemplate restTemplate = new RestTemplate();

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private XmlUtil xmlUtil;

	@Autowired
	private PharmacyRequestRepository priorRequestRepository;

	@Autowired
	private ClinicalRequestRepository clinicalRequestRepository;

	@Autowired
	private UploadERxRequestClinicRepository uploadERxRequestClinicRepository;

	@Autowired
	private UploadERxRequestPharmacyRepository uploadERxRequestPharmacyRepository;

	@Autowired
	private GeteRxTransactionRequestRepository geteRxTransactionRequestRepository;

	@Autowired
	private SearchTransactionsRepository searchTransactionsRepository;

	@Autowired
	private DownloadTransactionFileRepository downloadTransactionFileRepository;

	@Autowired
	private GetNewTransactionsRepository getNewTransactionsRepository;

	@Autowired
	private SetTransactionDownloadedRepository setTransactionDownloadedRepository;

	public static final Logger log = LoggerFactory.getLogger(EclaimService.class);

	@Autowired
	private PharmacyEntityMapper dtoToEntityMapper;

	@Autowired
	private ClinicalERXEntityMapper dtoClinicalToEntityMapper;

	public ResponseEntity<String> sendPriorRequestToEclaim(PharamacyErxDTO priorRequest) {
		try {
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
//			String xmlFormat = new String(xmlPayload,StandardCharsets.UTF_8);
			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);
			System.out.println("XML Payload byte[] length: " + xmlPayload.length);
			String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println(" XML Payload as String:\n ------>" + xmlFormat);

			PharmacyERXRequest entity = dtoToEntityMapper.toPharmacyERXRequest(priorRequest);
			priorRequestRepository.save(entity);
			log.info("PriorRequest saved to database with ID: {}", entity.getId());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, requestEntity, String.class);

			MediaType mediaType = response.getHeaders().getContentType();
			System.out.println(mediaType);

			System.out.println("Response Status Code: " + response.getStatusCodeValue());
			System.out.println("Response Body:\n" + response.getBody());
			log.info("Response Status Code: ", response.getStatusCodeValue());
			log.info("Response Body:\n", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("Exception in sendPriorRequestToEclaim: ", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<String> sendclinicPriorRequest(ClinicalErxRequestDTO priorRequest) {
		try {
			log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
			byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
//			String xmlFormat = new String(xmlPayload,StandardCharsets.UTF_8);
			System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);
			System.out.println("XML Payload byte[] length: " + xmlPayload.length);
			String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
			System.out.println(" XML Payload as String:\n ------>" + xmlFormat);

			PriorRequest entity = dtoClinicalToEntityMapper.toPriorRequest(priorRequest);
			clinicalRequestRepository.save(entity);
			log.info("PriorRequest saved to database with ID: {}", entity.getId());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, requestEntity, String.class);

			MediaType mediaType = response.getHeaders().getContentType();
			System.out.println(mediaType);

			System.out.println("Response Status Code: " + response.getStatusCodeValue());
			System.out.println("Response Body:\n" + response.getBody());
			log.info("Response Status Code: ", response.getStatusCodeValue());
			log.info("Response Body:\n", response.getBody());
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			log.error("Exception in sendPriorRequestToEclaim: ", e);
			return ResponseEntity.status(500).body("Error: " + e.getMessage());
		}
	}

	// public ResponseEntity<String> sendPriorRequestToEclaim(PharamacyErxDTO
	// priorRequest) {
	// try {
	// log.debug("XML Format ", xmlUtil.convertToXml(priorRequest));
	// byte[] xmlPayload = xmlUtil.convertToXml(priorRequest);
	// System.out.println("Convert XML to byte[]\n------------>" + xmlPayload);
	// System.out.println("XML Payload byte[] length: " + xmlPayload.length);
	// String xmlFormat = new String(xmlPayload, StandardCharsets.UTF_8);
	// System.out.println(" XML Payload as String:\n ------>" + xmlFormat);
	// PharmacyERXRequest entity =
	// dtoToEntityMapper.toPharmacyERXRequest(priorRequest); // Now matches the DTO
	// input
	// priorRequestRepository.save(entity);
	// log.info("PharmacyERXRequest saved to database with ID: {}", entity.getId());
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_XML);
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl,
	// requestEntity, String.class);
	// log.info("Response Status Code: ", response.getStatusCodeValue());
	// log.info("Response Body:\n", response.getBody());
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// log.error("Exception in sendPriorRequestToEclaim: ", e);
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }

	public ResponseEntity<String> uploadERxRequest(UploadERxRequestForUserDTO requestFromUser) {
		log.info("Received eRx upload request: {}", requestFromUser);
		try {
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
			entity.setUniqId(new Generator().generateERxReferenceNo());
			entity.setFacilityLogin(request.getFacilityLogin());
			entity.setFacilityPwd(request.getFacilityPwd());
			entity.setClinicianLogin(request.getClinicianLogin());
			entity.setClinicianPwd(request.getClinicianPwd());
			entity.setFileName(request.getFileName());
			entity.setFileContent(xmlPayload);

			// Save to DB
			UploadERxRequest savedEntity = uploadERxRequestClinicRepository.save(entity);
			log.info("Saved UploadERxRequest with ID: {}, uniqId: {}", savedEntity.getId(), savedEntity.getUniqId());

			// Send to external API
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlUtil.convertToXml(request), headers);

			String fullUrl = baseUrl + "/UploadERxRequest"; // Try case-sensitive endpoint (update as per API docs)
			log.debug("Sending POST request to URL: {}, Headers: {}, Payload: {}", fullUrl, headers, xmlString);

			try {
				ResponseEntity<String> apiResponse = restTemplate.postForEntity(fullUrl, requestEntity, String.class);
				log.info("External API response: Status: {}, Body: {}", apiResponse.getStatusCodeValue(),
						apiResponse.getBody());

				// Update entity with response details
				savedEntity.setERxReferenceNo("ERX-" + generator.generateUUID()); // Generate unique eRxReferenceNo
				savedEntity.setErrorMessage(null); // Clear error message on success
				savedEntity.setErrorReport(null); // Clear error report on success
				uploadERxRequestClinicRepository.save(savedEntity);

				// Prepare response
				UploadERxRequestResponseDTO responseDTO = new UploadERxRequestResponseDTO();
				responseDTO.setERxReferenceNo(savedEntity.getUniqId());
				responseDTO.setStatus("SUCCESS");
				responseDTO.setErrorMessage(null);
				responseDTO.setErrorReport(null);

				return ResponseEntity.ok(objectMapper.writeValueAsString(responseDTO));

			} catch (HttpClientErrorException.NotFound ex) {
				// Handle 404 specifically
				log.error("404 Not Found for URL: {}", fullUrl, ex);
				savedEntity.setErrorMessage("404 Not Found: " + ex.getMessage());
				savedEntity.setErrorReport(ex.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
				uploadERxRequestClinicRepository.save(savedEntity);

				return buildErrorResponse(404, "API endpoint not found: " + fullUrl, "Please verify the endpoint URL");
			} catch (HttpClientErrorException ex) {
				// Handle other HTTP errors
				log.error("HTTP error occurred: {}", ex.getMessage(), ex);
				savedEntity.setErrorMessage(ex.getStatusCode() + ": " + ex.getMessage());
				savedEntity.setErrorReport(ex.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
				uploadERxRequestClinicRepository.save(savedEntity);

				return buildErrorResponse(ex.getStatusCode().value(), ex.getMessage(), "API request failed");
			}

		} catch (JAXBException e) {
			log.error("XML processing error", e);
			return buildErrorResponse(500, "XML Error: " + e.getMessage(), "Failed to process XML");
		} catch (Exception e) {
			log.error("Unexpected error", e);
			return buildErrorResponse(500, "Error: " + e.getMessage(), "Unexpected error occurred");
		}
	}

	public ResponseEntity<String> uploadPharmacy(UploadPharmacyForUserERxRequestForUserDTO requestFromUser) {
		log.info("Received eRx upload request: {}", requestFromUser);
		try {
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
			UploadERxPharmacyRequestDTO request = new UploadERxPharmacyRequestDTO();
			request.setFacilityLogin(requestFromUser.getFacilityLogin());
			request.setFacilityPwd(requestFromUser.getFacilityPwd());
			request.setClinicianLogin(requestFromUser.getClinicianLogin());
			request.setClinicianPwd(requestFromUser.getClinicianPwd());
			request.setFileName(requestFromUser.getFileName());
			request.setFileContent(Base64.getEncoder().encode(xmlPayload));

			// Prepare entity
			PharmacyUploadERX entity = new PharmacyUploadERX();
			entity.setUniqId(new Generator().generateERxReferenceNo());
			entity.setFacilityLogin(request.getFacilityLogin());
			entity.setFacilityPwd(request.getFacilityPwd());
			entity.setClinicianLogin(request.getClinicianLogin());
			entity.setClinicianPwd(request.getClinicianPwd());
			entity.setFileName(request.getFileName());
			entity.setFileContent(xmlPayload);

			// Save to DB
			PharmacyUploadERX savedEntity = uploadERxRequestPharmacyRepository.save(entity);
			log.info("Saved UploadERxRequest with ID: {}, uniqId: {}", savedEntity.getId(), savedEntity.getUniqId());

			// Send to external API
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON));
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlUtil.convertToXml(request), headers);

			String fullUrl = baseUrl + "/uploadPharmacyERxRequest"; // Try case-sensitive endpoint (update as per API
																	// docs)
			log.debug("Sending POST request to URL: {}, Headers: {}, Payload: {}", fullUrl, headers, xmlString);

			try {
				ResponseEntity<String> apiResponse = restTemplate.postForEntity(fullUrl, requestEntity, String.class);
				log.info("External API response: Status: {}, Body: {}", apiResponse.getStatusCodeValue(),
						apiResponse.getBody());

				// Update entity with response details
				savedEntity.setERxReferenceNo("ERX-" + generator.generateUUID()); // Generate unique eRxReferenceNo
				savedEntity.setErrorMessage(null); // Clear error message on success
				savedEntity.setErrorReport(null); // Clear error report on success
				uploadERxRequestPharmacyRepository.save(savedEntity);

				// Prepare response
				PharmacyERXRequestResponseDTO responseDTO = new PharmacyERXRequestResponseDTO();
				responseDTO.setERxReferenceNo(savedEntity.getUniqId());
				responseDTO.setStatus("SUCCESS");
				responseDTO.setErrorMessage(null);
				responseDTO.setErrorReport(null);

				return ResponseEntity.ok(objectMapper.writeValueAsString(responseDTO));

			} catch (HttpClientErrorException.NotFound ex) {
				// Handle 404 specifically
				log.error("404 Not Found for URL: {}", fullUrl, ex);
				savedEntity.setErrorMessage("404 Not Found: " + ex.getMessage());
				savedEntity.setErrorReport(ex.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
				uploadERxRequestPharmacyRepository.save(savedEntity);

				return buildErrorResponse(404, "API endpoint not found: " + fullUrl, "Please verify the endpoint URL");
			} catch (HttpClientErrorException ex) {
				// Handle other HTTP errors
				log.error("HTTP error occurred: {}", ex.getMessage(), ex);
				savedEntity.setErrorMessage(ex.getStatusCode() + ": " + ex.getMessage());
				savedEntity.setErrorReport(ex.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
				uploadERxRequestPharmacyRepository.save(savedEntity);

				return buildErrorResponse(ex.getStatusCode().value(), ex.getMessage(), "API request failed");
			}

		} catch (JAXBException e) {
			log.error("XML processing error", e);
			return buildErrorResponse(500, "XML Error: " + e.getMessage(), "Failed to process XML");
		} catch (Exception e) {
			log.error("Unexpected error", e);
			return buildErrorResponse(500, "Error: " + e.getMessage(), "Unexpected error occurred");
		}
	}

	private ResponseEntity<String> buildErrorResponse(int status, String errorMessage, String errorReport) {
		UploadERxRequestResponseDTO responseDTO = new UploadERxRequestResponseDTO();
		responseDTO.setStatus("ERROR");
		responseDTO.setErrorMessage(errorMessage);
		responseDTO.setErrorReport(errorReport != null ? errorReport.getBytes(StandardCharsets.UTF_8) : null);
		try {
			return ResponseEntity.status(status).body(objectMapper.writeValueAsString(responseDTO));
		} catch (JsonProcessingException e) {
			log.error("Failed to serialize error response", e);
			return ResponseEntity.status(500)
					.body("{\"status\":\"ERROR\",\"errorMessage\":\"Failed to serialize response\"}");
		}
	}

	@Transactional
	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO requestDTO) {
		if (requestDTO == null) {
			return ResponseEntity.badRequest().body("Request DTO cannot be null");
		}

		// Create entity for DB storage
		GetNewTransactionsRequest record = new GetNewTransactionsRequest();
		record.setLogin(requestDTO.getLogin());
		record.setPwd(requestDTO.getPwd());
		record.setRequestedAt(LocalDateTime.now());
		record.setResult(0); // Default to failure

		try {
			// 1. Verify the correct endpoint URL - make this configurable
			String apiEndpoint = baseUrl + "/GetNewTransactions"; // Note case sensitivity

			// 2. Convert DTO to XML
			byte[] xmlPayload = xmlUtil.convertToXml(requestDTO);
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);

			// 3. Prepare headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

			// 4. Make the API call
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
			ResponseEntity<String> response;

			try {
				response = restTemplate.postForEntity(apiEndpoint, requestEntity, String.class);
			} catch (HttpClientErrorException.NotFound ex) {
				// Handle 404 specifically
				String errorMsg = "API endpoint not found. Please verify: " + apiEndpoint;
				record.setErrorMessage(errorMsg);
				record.setXmlTransactions(xmlString);
				getNewTransactionsRepository.save(record);
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("{\"status\":\"ERROR\",\"message\":\"" + errorMsg + "\"}");
			}

			// 5. Process successful response
			String responseBody = response.getBody();
			record.setResponseStatus(response.getStatusCode().toString());
			record.setResult(1); // Mark as success

			// Parse and store response
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(GetNewTransactionsResponseDTO.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				GetNewTransactionsResponseDTO responseDTO = (GetNewTransactionsResponseDTO) unmarshaller
						.unmarshal(new StringReader(responseBody));

				record.setXmlTransactions(responseDTO.getXmlTransactions());
				record.setErrorMessage(responseDTO.getErrorMessage());
			} catch (Exception e) {
				log.warn("Failed to parse response", e);
				record.setErrorMessage("Response parsing error");
			}

			// Save to DB
			getNewTransactionsRepository.save(record);
			return ResponseEntity.ok(responseBody);

		} catch (Exception e) {
			log.error("GetNewTransactions failed", e);
			record.setErrorMessage("Error: " + e.getMessage());
			getNewTransactionsRepository.save(record);
			return ResponseEntity.internalServerError()
					.body("{\"status\":\"ERROR\",\"message\":\"" + e.getMessage() + "\"}");
		}
	}

//	@Transactional
//	public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO requestDTO) {
//		if (requestDTO == null) {
//			throw new IllegalArgumentException("Missing requestDTO");
//		}
//
//		GetNewTransactionsRequest record = new GetNewTransactionsRequest();
//		record.setLogin(requestDTO.getLogin());
//		record.setPwd(requestDTO.getPwd());
//		record.setRequestedAt(LocalDateTime.now());
//		record.setResult(0);
//
//		try {
////			String fullUrl = baseUrl;
//			// Convert DTO to XML string
//			String fullUrl = baseUrl + "/GetNewTransactions";
//			
//			byte[] xmlPayload = xmlUtil.convertToXml(requestDTO);
//			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
////			record.setXmlTransactions(xmlString); // Store request XML
//
//			// Set headers and send request
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_XML);
//			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
//			HttpEntity<String> requestEntity = new HttpEntity<>(xmlString, headers);
//
//			ResponseEntity<String> response = restTemplate.postForEntity(fullUrl, requestEntity, String.class);
//
//			String responseBody = response.getBody();
//			log.info("Received response:\n{}", responseBody);
//
//			// Process XML response and update record
//			processResponse(record, responseBody);
//			record.setResponseStatus(response.getStatusCode().toString());
//
//			getNewTransactionsRepository.save(record);
//			return ResponseEntity.status(response.getStatusCode()).body(responseBody);
//
//		} catch (Exception e) {
//			log.error("GetNewTransactions failed: {}", e.getMessage(), e);
//			record.setResponseStatus("error");
//			record.setErrorMessage("Error: " + e.getMessage());
//
//			// Save the error record with default result = 0
//			getNewTransactionsRepository.save(record);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
//		}
//	}

//	private void processResponse(GetNewTransactionsRequest record, String responseBody) {
//		// String cleanedResponse = cleanXmlResponse(responseBody);
//
//		try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(GetNewTransactionsResponseDTO.class);
//			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//			GetNewTransactionsResponseDTO responseDTO = (GetNewTransactionsResponseDTO) unmarshaller
//					.unmarshal(new StringReader(responseBody));
//
//			record.setXmlTransactions(responseDTO.getXmlTransactions());
//			record.setErrorMessage(responseDTO.getErrorMessage());
//
//			// If success, mark result = 1
//			record.setResult(responseDTO.getResult());
//
//		} catch (Exception ex) {
//			log.warn("JAXB failed, trying fallback XML: {}", ex.getMessage());
//			record.setResult(1); // Or keep 0 to indicate JAXB failure
//		}
//	}

	// Fallback XML parsing
	// try {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",
	// true);
	// DocumentBuilder builder = factory.newDocumentBuilder();
	// Document doc = builder.parse(new InputSource(new
	// StringReader(cleanedResponse)));
	// doc.getDocumentElement().normalize();
	//
	// NodeList xmlNode = doc.getElementsByTagName("XmlTransactions");
	// if (xmlNode.getLength() > 0) {
	// record.setXmlTransactions(xmlNode.item(0).getTextContent().trim());
	// }
	//
	// NodeList errorMsgNode = doc.getElementsByTagName("ErrorMessage");
	// if (errorMsgNode.getLength() > 0) {
	// record.setErrorMessage(errorMsgNode.item(0).getTextContent().trim());
	// }
	//
	// } catch (Exception e) {
	// log.error("Fallback XML parsing failed", e);
	// record.setErrorMessage("Failed to parse response: " + e.getMessage());
	// }
	// }

	// private String cleanXmlResponse2(String xml) {
	// if (xml == null)
	// return "";
	// String cleaned = xml.replaceAll("^[\\W]+<\\?xml", "<?xml");
	// if (!cleaned.startsWith("<?xml")) {
	// cleaned = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + cleaned;
	// }
	// return cleaned;
	// }

	// public ResponseEntity<String> getNewTransactions(GetNewTransactionsRequestDTO
	// dto) {
	// try {
	// byte[] xmlPayload = xmlUtil.convertToXml(dto);
	// System.out.println("xmlPayload=>"+xmlPayload.toString());
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_XML);
	// headers.set("login", dto.getLogin());
	// headers.set("pwd", dto.getPwd());
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	//
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl +
	// "/GetNewTransactions",
	// requestEntity, String.class);
	//
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }

	@Transactional
	public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO dto) {
		if (dto == null) {
			throw new IllegalArgumentException("Missing request DTO");
		}

		GeteRxTransactionReques record = new GeteRxTransactionReques();
		record.setLogin(dto.getLogin());
		record.setPwd(dto.getPwd());
		record.setMemberID(dto.getMemberID());
		record.setERxReferenceNo(dto.geteRxReferenceNo());
		record.setRequestedAt(LocalDateTime.now());

		try {

			byte[] xmlPayload = xmlUtil.convertToXml(dto);
			String xmlString = new String(xmlPayload, StandardCharsets.UTF_8);
			record.setXmlTransactions(xmlString);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
			HttpEntity<String> requestEntity = new HttpEntity<>(xmlString, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/GeteRxTransaction", requestEntity,
					String.class);

			String responseBody = response.getBody();
			log.info("Received GeteRxTransaction response:\n{}", responseBody);

			processGeteRxTransactionResponse(record, responseBody);
			record.setResponseStatus(response.getStatusCode().toString());

			geteRxTransactionRequestRepository.save(record);

			return ResponseEntity.status(response.getStatusCode()).body(responseBody);

		} catch (Exception e) {
			log.error("GeteRxTransaction failed: {}", e.getMessage(), e);
			record.setResponseStatus("ERROR");
			record.setErrorMessage("Exception: " + e.getMessage());
			geteRxTransactionRequestRepository.save(record);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	private void processGeteRxTransactionResponse(GeteRxTransactionReques record, String responseBody) {
		// String cleanedResponse = cleanXmlResponse(responseBody); // optional if DHA
		// wraps XML with headers

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GeteRxTransactionResponseDTO.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			GeteRxTransactionResponseDTO responseDTO = (GeteRxTransactionResponseDTO) unmarshaller
					.unmarshal(new StringReader(responseBody));

			record.setXmlTransactions(responseDTO.getXmlTransactions());
			record.setErrorMessage(responseDTO.getErrorMessage());
			record.setErxTransactionResult(responseDTO.getErxTransactionResult());

		} catch (Exception e) {
			log.warn("Fallback XML parsing failed: {}", e.getMessage());
			record.setErrorMessage("Parsing failed: " + e.getMessage());
			record.setErxTransactionResult(1);
		}
	}

	// if (responseDTO.getXmlTransactions() != null &&
	// !responseDTO.getXmlTransactions().isEmpty()) {
	// JAXBContext innerCtx = JAXBContext.newInstance(PriorRequest.class);
	// Unmarshaller innerUnmarshaller = innerCtx.createUnmarshaller();
	// PriorRequest erxData = (PriorRequest) innerUnmarshaller.unmarshal(
	// new StringReader(responseDTO.getXmlTransactions()));
	//
	// // 🔧 Set additional required fields manually
	// erxData.setUniqId(UUID.randomUUID().toString());
	// erxData.setCreateBy("system"); // or fetch from logged-in user
	// erxData.setCreateOn(Instant.now().getEpochSecond());
	//
	// // Optionally log to debug
	// log.info("Saving PriorRequest: {}", erxData);
	// System.out.println("Saving PriorRequest: {}" + erxData);
	//
	// eclaimRepository.save(erxData);
	// }

	// public ResponseEntity<String> geteRxTransaction(GeteRxTransactionRequestDTO
	// dto) {
	//
	// try {
	// byte[] xmlPayload = xmlUtil.convertToXml(dto);
	// HttpHeaders headers = new HttpHeaders();
	// headers.set("login", dto.getLogin());
	// headers.set("pwd", dto.getPwd());
	// headers.set("memberID", String.valueOf(dto.getMemberID()));
	// headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
	//
	// headers.setContentType(MediaType.APPLICATION_XML);
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	//
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl +
	// "/GeteRxTransaction",
	// requestEntity, String.class);
	//
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }

	@Transactional
	public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO dto) {
		SearchTransactionsRequest record = new SearchTransactionsRequest();

		record.setLogin(dto.getLogin());
		record.setPwd(dto.getPwd());
		record.setDirection(dto.getDirection());
		record.setCallerLicense(dto.getCallerLicense());
		record.setClinicianLicense(dto.getClinicianLicense());
		record.setMemberID(dto.getMemberID());
		record.setERxReferenceNo(dto.geteRxReferenceNo());
		record.setTransactionStatus(dto.getTransactionStatus());

		record.setTransactionFromDate(dto.getTransactionFromDate());
		record.setTransactionToDate(dto.getTransactionToDate());

		record.setMinRecordCount(dto.getMinRecordCount());
		record.setMaxRecordCount(dto.getMaxRecordCount());
		record.setRequestedAt(LocalDateTime.now());

		try {

			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);

			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/SearchTransactions", requestEntity,
					String.class);

			String responseBody = response.getBody();
			record.setResponseStatus(response.getStatusCode().toString());

			record.setFoundTransactions(responseBody);

			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(SearchTransactionsResponseDTO.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				SearchTransactionsResponseDTO responseDTO = (SearchTransactionsResponseDTO) unmarshaller
						.unmarshal(new StringReader(responseBody));

				record.setFoundTransactions(responseDTO.getFoundTransactions());
				record.setErrorMessage(responseDTO.getErrorMessage());
				record.setSearchTransactionResult(responseDTO.getSearchTransactionsResult());

			} catch (Exception ex) {
				log.warn("Failed to parse SearchTransactions response XML: {}", ex.getMessage());
				record.setErrorMessage("Parsing failed: " + ex.getMessage());
				record.setSearchTransactionResult(1);
			}

			// Save the record
			searchTransactionsRepository.save(record);

			// Return the raw response back to the caller
			return ResponseEntity.status(response.getStatusCode()).body(responseBody);

		} catch (Exception e) {
			log.error("searchTransactions failed: {}", e.getMessage(), e);
			record.setResponseStatus("ERROR");
			record.setErrorMessage("Exception: " + e.getMessage());
			searchTransactionsRepository.save(record);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	// public ResponseEntity<String> searchTransactions(SearchTransactionsRequestDTO
	// dto) {
	// DateTimeFormatter formatter =
	// DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	// try {
	// byte[] xmlPayload = xmlUtil.convertToXml(dto);
	// HttpHeaders headers = new HttpHeaders();
	// headers.set("login", dto.getLogin());
	// headers.set("pwd", dto.getPwd());
	// headers.set("direction", dto.getDirection());
	// headers.set("callerLicense", dto.getCallerLicense());
	// headers.set("clinicianLicense", dto.getClinicianLicense());
	// headers.set("memberID", String.valueOf(dto.getMemberID()));
	// headers.set("eRxReferenceNo", String.valueOf(dto.geteRxReferenceNo()));
	// headers.set("transactionStatus", dto.getTransactionStatus());
	// headers.set("transactionFromDate",
	// dto.getTransactionFromDate().format(formatter));
	// headers.set("transactionToDate",
	// dto.getTransactionFromDate().format(formatter));
	// headers.set("minRecordCount", String.valueOf(dto.getMinRecordCount()));
	// headers.set("maxRecordCount", String.valueOf(dto.getMaxRecordCount()));
	// headers.setContentType(MediaType.APPLICATION_XML);
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	//
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl +
	// "/SearchTransactions",
	// requestEntity, String.class);
	//
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }

	@Transactional
	public ResponseEntity<String> downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
		DownloadTransactionFileRequest record = new DownloadTransactionFileRequest();
		record.setLogin(dto.getLogin());
		record.setPwd(dto.getPwd());
		record.setFileID(dto.getFileID());
		record.setRequestedAt(LocalDateTime.now());

		try {
			// Convert DTO to XML bytes
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			// Prepare HTTP headers and entity
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			// Call external DHA web service
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/DownloadTransactionFile",
					requestEntity, String.class);

			String responseBody = response.getBody();
			record.setResponseStatus(response.getStatusCode().toString());

			// Parse response XML with JAXB
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(DownloadTransactionFileResponseDTO.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				DownloadTransactionFileResponseDTO responseDTO = (DownloadTransactionFileResponseDTO) unmarshaller
						.unmarshal(new StringReader(responseBody));

				record.setFileName(responseDTO.getFileName());
				record.setFile(responseDTO.getFile());
				record.setErrorMessage(responseDTO.getErrorMessage());

			} catch (Exception e) {
				log.warn("Failed to parse DownloadTransactionFile response XML: {}", e.getMessage());
				record.setErrorMessage("XML parsing error: " + e.getMessage());
			}

			// Save record to DB
			downloadTransactionFileRepository.save(record);

			// Return original response
			return ResponseEntity.status(response.getStatusCode()).body(responseBody);

		} catch (Exception e) {
			record.setResponseStatus("ERROR");
			record.setErrorMessage("Exception: " + e.getMessage());
			downloadTransactionFileRepository.save(record);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	// public ResponseEntity<String>
	// downloadTransactionFile(DownloadTransactionFileRequestDTO dto) {
	// try {
	// byte[] xmlPayload = xmlUtil.convertToXml(dto);
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_XML);
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	//
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl +
	// "/DownloadTransactionFile",
	// requestEntity, String.class);
	//
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }

	@Transactional
	public ResponseEntity<String> setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
		SetTransactionDownloadedRequest record = new SetTransactionDownloadedRequest();
		record.setLogin(dto.getLogin());
		record.setPwd(dto.getPwd());
		record.setFileID(dto.getFileID());
		record.setRequestedAt(LocalDateTime.now());

		try {
			// Convert DTO to XML
			byte[] xmlPayload = xmlUtil.convertToXml(dto);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);

			// Call external DHPO/eRx web service
			ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/SetTransactionDownloaded",
					requestEntity, String.class);

			String responseBody = response.getBody();
			record.setResponseStatus(response.getStatusCode().toString());

			// Parse response XML
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(SetTransactionDownloadedResponseDTO.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				SetTransactionDownloadedResponseDTO responseDTO = (SetTransactionDownloadedResponseDTO) unmarshaller
						.unmarshal(new StringReader(responseBody));

				record.setErrorMessage(responseDTO.getErrorMessage());
				record.setSetTransactionDownloadedResult(responseDTO.getSetTransactionDownloadedResult());

			} catch (Exception e) {
				log.warn("Failed to parse SetTransactionDownloaded response XML: {}", e.getMessage());
				record.setErrorMessage("Parsing failed: " + e.getMessage());
				record.setSetTransactionDownloadedResult(1);
			}

			// Persist record
			setTransactionDownloadedRepository.save(record);

			return ResponseEntity.status(response.getStatusCode()).body(responseBody);

		} catch (Exception e) {
			log.error("Exception in setTransactionDownloaded: {}", e.getMessage(), e);
			record.setResponseStatus("ERROR");
			record.setErrorMessage("Exception: " + e.getMessage());
			setTransactionDownloadedRepository.save(record);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}
	}

	// public ResponseEntity<String>
	// setTransactionDownloaded(SetTransactionDownloadedRequestDTO dto) {
	// try {
	// byte[] xmlPayload = xmlUtil.convertToXml(dto);
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.APPLICATION_XML);
	// HttpEntity<byte[]> requestEntity = new HttpEntity<>(xmlPayload, headers);
	//
	// ResponseEntity<String> response = restTemplate.postForEntity(eclaimUrl +
	// "/SetTransactionDownloaded",
	// requestEntity, String.class);
	//
	// return
	// ResponseEntity.status(response.getStatusCode()).body(response.getBody());
	// } catch (Exception e) {
	// return ResponseEntity.status(500).body("Error: " + e.getMessage());
	// }
	// }
}

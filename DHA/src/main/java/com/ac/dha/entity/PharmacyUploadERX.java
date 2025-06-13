package com.ac.dha.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_eclaim_PharmacyUploadERX")
public class PharmacyUploadERX {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uniq_id")
	private Integer uniqId;

	@Column(name = "facility_login", length = 100)
	private String facilityLogin;

	@Column(name = "facility_pwd", length = 100)
	private String facilityPwd;

	@Column(name = "clinician_login", length = 100)
	private String clinicianLogin;

	@Column(name = "clinician_pwd", length = 100)
	private String clinicianPwd;

	@Column(name = "file_name", length = 255)
	private String fileName;

	@Column(name = "e_rx_reference_no", length = 100)
	private String eRxReferenceNo;

	@Lob
	@Column(name = "error_message")
	private String errorMessage;

	@Lob
	@Column(name = "file_content", columnDefinition = "TEXT")
	private byte[] fileContent;

	@Lob
	@Column(name = "error_report", columnDefinition = "TEXT")
	private byte[] errorReport;
}

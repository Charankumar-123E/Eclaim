package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entity.DownloadTransactionFileRequest;

public interface DownloadTransactionFileRepository extends JpaRepository<DownloadTransactionFileRequest, Long> {

}

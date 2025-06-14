package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.DownloadTransactionFileRequest;
@Repository
public interface DownloadTransactionFileRepository extends JpaRepository<DownloadTransactionFileRequest, Long> {

}

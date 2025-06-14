package com.ac.dha.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.SetTransactionDownloadedRequest;
@Repository
public interface CSetTransactionDownloadedRepository extends JpaRepository<SetTransactionDownloadedRequest, Long> {

}

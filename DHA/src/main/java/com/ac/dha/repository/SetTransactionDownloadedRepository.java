package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.SetTransactionDownloadedRequest;
@Repository
public interface SetTransactionDownloadedRepository extends JpaRepository<SetTransactionDownloadedRequest, Long> {

}

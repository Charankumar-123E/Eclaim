package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.GeteRxTransactionReques;

@Repository
public interface GeteRxTransactionRequestRepository extends JpaRepository<GeteRxTransactionReques, Long> {

}

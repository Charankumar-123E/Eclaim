package com.ac.dha.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.GeteRxTransactionReques;

@Repository
public interface CGeteRxTransactionRequestRepository extends JpaRepository<GeteRxTransactionReques, Long> {

}
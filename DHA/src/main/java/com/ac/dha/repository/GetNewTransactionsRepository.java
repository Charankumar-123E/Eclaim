package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entity.GetNewTransactionsRequest;

public interface GetNewTransactionsRepository extends JpaRepository<GetNewTransactionsRequest, Long> {

}

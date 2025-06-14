package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.GetNewTransactionsRequest;
@Repository
public interface GetNewTransactionsRepository extends JpaRepository<GetNewTransactionsRequest, Long> {

}

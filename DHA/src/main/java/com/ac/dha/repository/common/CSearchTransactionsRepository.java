package com.ac.dha.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.SearchTransactionsRequest;
@Repository
public interface CSearchTransactionsRepository extends JpaRepository<SearchTransactionsRequest, Long> {

}
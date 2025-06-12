package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac.dha.entity.SearchTransactionsRequest;

public interface SearchTransactionsRepository extends JpaRepository<SearchTransactionsRequest, Long> {

}

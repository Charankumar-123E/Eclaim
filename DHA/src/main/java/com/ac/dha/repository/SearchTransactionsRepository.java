package com.ac.dha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ac.dha.entity.SearchTransactionsRequest;
@Repository
public interface SearchTransactionsRepository extends JpaRepository<SearchTransactionsRequest, Long> {

}

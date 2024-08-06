package com.transaction.producer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.producer.dto.TransactionDto;

@Repository
public interface TrandactionDao extends JpaRepository<TransactionDto, Long>{

}

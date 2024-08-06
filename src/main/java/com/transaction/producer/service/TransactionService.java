package com.transaction.producer.service;

import com.transaction.producer.vo.TransactionVo;

public interface TransactionService {
	
	public TransactionVo newTransaction(TransactionVo transactionVo);
}

package com.transaction.producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.producer.service.TransactionService;
import com.transaction.producer.vo.TransactionVo;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@PostMapping("/")
	public TransactionVo newTransaction(@RequestBody TransactionVo transactionVo){
		transactionVo = transactionService.newTransaction(transactionVo);
		return transactionVo;
	}
}

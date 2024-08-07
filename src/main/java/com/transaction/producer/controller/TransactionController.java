package com.transaction.producer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.producer.service.TransactionService;
import com.transaction.producer.vo.TransactionVo;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}

	@PostMapping("/")
	public ResponseEntity<TransactionVo> newTransaction(@RequestBody TransactionVo transactionVo) {
		LOGGER.info("endpoint invoked for new transaction :: start");
		transactionVo = transactionService.newTransaction(transactionVo);
		ResponseEntity<TransactionVo> responseEntity = new ResponseEntity<TransactionVo>(transactionVo, HttpStatus.OK);
		LOGGER.info("endpoint invoked for new transaction :: complete");
		return responseEntity;
	}
}

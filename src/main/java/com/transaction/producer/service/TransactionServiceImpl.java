package com.transaction.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.transaction.producer.dao.TrandactionDao;
import com.transaction.producer.dto.TransactionDto;
import com.transaction.producer.kafka.KafkaProducer;
import com.transaction.producer.util.VoDtoConvertor;
import com.transaction.producer.vo.TransactionVo;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private final TrandactionDao trandactionDao;
	private final VoDtoConvertor voDtoConvertor;
	private final KafkaProducer kafkaProducer;

	public TransactionServiceImpl(TrandactionDao trandactionDao, VoDtoConvertor voDtoConvertor,
			KafkaProducer kafkaProducer) {
		super();
		this.trandactionDao = trandactionDao;
		this.voDtoConvertor = voDtoConvertor;
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public TransactionVo newTransaction(TransactionVo transactionVo) {
		LOGGER.info("service layer invoked for new transaction :: start");
		transactionVo.setStatus("N");// Status flag for new transaction set as N
		TransactionDto transactionDto = voDtoConvertor.convert(transactionVo);
		LOGGER.info("VO to DTO conversion done");
		transactionDto = trandactionDao.save(transactionDto);
		transactionVo = voDtoConvertor.convert(transactionDto);
		LOGGER.info("DTO to VO conversion done");
		LOGGER.info("Pusing message to kafka :: " + transactionVo);
		kafkaProducer.pushMessage(transactionVo);
		LOGGER.info("service layer invoked for new transaction :: complete");
		return transactionVo;
	}

}

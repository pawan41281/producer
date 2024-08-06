package com.transaction.producer.service;

import org.springframework.stereotype.Service;

import com.transaction.producer.dao.TrandactionDao;
import com.transaction.producer.dto.TransactionDto;
import com.transaction.producer.kafka.KafkaProducer;
import com.transaction.producer.util.VoDtoConvertor;
import com.transaction.producer.vo.TransactionVo;

@Service
public class TransactionServiceImpl implements TransactionService {

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
		transactionVo.setStatus("N");// Status flag for new transaction set as N
		TransactionDto transactionDto = voDtoConvertor.convert(transactionVo);
		transactionDto = trandactionDao.save(transactionDto);
		transactionVo = voDtoConvertor.convert(transactionDto);
		
		//String message = transactionVo.toString();
		//kafkaProducer.pushMessage(message);
		
		kafkaProducer.pushMessage(transactionVo);
		
		return transactionVo;
	}

}

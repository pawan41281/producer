package com.transaction.producer.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.transaction.producer.vo.TransactionVo;

@Service
public class KafkaProducer {

	private final KafkaTemplate<String, TransactionVo> kafkaTemplate;
	
	@Value("${app.kafka.topic}")
	private String kafkaTopic;

	public KafkaProducer(KafkaTemplate<String, TransactionVo> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void pushMessage(TransactionVo transactionVo) {
		Message<TransactionVo> message = MessageBuilder
				.withPayload(transactionVo)
				.setHeader(KafkaHeaders.TOPIC, kafkaTopic)
				.build();
				
		kafkaTemplate.send(message);
	}
	
}

package com.transaction.producer.scheduler;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.transaction.producer.config.WebComponentConfig;
import com.transaction.producer.util.CreditCardNumberGenerator;
import com.transaction.producer.vo.TransactionVo;

@EnableScheduling
@Configuration
public class TransactionScheduler {

	private final WebComponentConfig webComponentConfig;
	private final CreditCardNumberGenerator creditCardNumberGenerator;

	public TransactionScheduler(WebComponentConfig webComponentConfig, CreditCardNumberGenerator creditCardNumberGenerator) {
		super();
		this.webComponentConfig = webComponentConfig;
		this.creditCardNumberGenerator = creditCardNumberGenerator;
	}

	long id = 1L;
	String url = "http://localhost:8080/producerservice/v1/transactions/";

	private final String bins[] = {"572430","483312","571503","622126","622925","564182","633110","560221","560225","111111"};
	
	@Scheduled(cron = "*/10 * * * * *") // every 10 seconds
	public void initiateTransaction() {
		String bin = bins[ ((int)(Math.random()*10)) ];
		TransactionVo transactionVo = new TransactionVo();
		transactionVo.setTrandactionId(System.currentTimeMillis());
		transactionVo.setAmount(Math.random()*99999);
		transactionVo.setCardNumber( creditCardNumberGenerator.generateCCNumber(bin, 16) );
		transactionVo.setMerchantId("QWER1234");
		transactionVo.setStatus("N");
		transactionVo.setTimestamp(new Timestamp(System.currentTimeMillis()));
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		requestHeaders.set("accept", "*/*");
		requestHeaders.set("Content-Type", "application/json");
//		requestHeaders.set("Authorization", "Bearer your-token");
		HttpEntity<TransactionVo> requestEntity = new HttpEntity<TransactionVo>(transactionVo, requestHeaders);
		System.out.println("requestEntity :: " + requestEntity);
		RestTemplate restTemplate = webComponentConfig.getRestTemplate();
		ResponseEntity<?> responseEntity = restTemplate.postForEntity(url, requestEntity, TransactionVo.class);
		System.out.println("responseEntity :: " + responseEntity);
	}
}

package com.transaction.producer.vo;

import java.sql.Timestamp;

public class TransactionVo {

	private Long id;
	private Long trandactionId;
	private String cardNumber;
	private Double amount;
	private Timestamp timestamp;// transaction timestamp
	private String merchantId;
	private String status;

	public TransactionVo() {
		super();
	}

	public TransactionVo(Long id, Long trandactionId, String cardNumber, Double amount, Timestamp timestamp,
			String merchantId, String status) {
		super();
		this.id = id;
		this.trandactionId = trandactionId;
		this.cardNumber = cardNumber;
		this.amount = amount;
		this.timestamp = timestamp;
		this.merchantId = merchantId;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrandactionId() {
		return trandactionId;
	}

	public void setTrandactionId(Long trandactionId) {
		this.trandactionId = trandactionId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TransactionVo [id=" + id + ", trandactionId=" + trandactionId + ", cardNumber=" + cardNumber
				+ ", amount=" + amount + ", timestamp=" + timestamp + ", merchantId=" + merchantId + ", status="
				+ status + "]";
	}

}

package com.wallet.api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Statement")
public class Statement {
	@Id
	private String statementId;
	private String transactionType;
	private Double amount;
	private LocalDate date;
	private String transactionRemarks;
	
	



	public Statement(String statementId, String transactionType, Double amount, LocalDate date,
			String transactionRemarks) {
		super();
		this.statementId = statementId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
		
		this.transactionRemarks = transactionRemarks;
	}
	public Statement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStatementId() {
		return statementId;
	}
	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public String getTransactionRemarks() {
		return transactionRemarks;
	}
	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}

	
	
}

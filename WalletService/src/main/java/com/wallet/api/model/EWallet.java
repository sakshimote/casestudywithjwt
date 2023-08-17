package com.wallet.api.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "EWallet")
public class EWallet {
	@Id
	private String walletId;
	private Double currentBalance;
	
	private String userId;
	
	private String username;
	
	@Field
	private List<Statement> statements;
	


	



	public EWallet(String walletId, Double currentBalance, String userId, String username, List<Statement> statements) {
		super();
		this.walletId = walletId;
		this.currentBalance = currentBalance;
		this.userId = userId;
		this.username = username;
		this.statements = statements;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public List<Statement> getStatements() {
		return statements;
	}



	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}





	public EWallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getWalletId() {
		return walletId;
	}
	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}


	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}


	
	

}

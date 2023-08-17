package com.wallet.api.service;

import java.util.List;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;
import com.wallet.api.model.OrderPaymentStatement;


public interface EWalletService {
	
	public List<EWallet> getWallets();
	
	public EWallet addWallet(EWallet ewallet,String userId);
	
	public EWallet addMoney(String walletId,Double amount);
	
	
	public EWallet getById(String walletId);
	
	
	public List<Statement> getStatements();
	
	public String deleteById(String walletId);
	
	public EWallet removeMoney(String walletId,Double amount);
	
	public  OrderPaymentStatement  payUsingWallet(Double amount,String walletId);
	public EWallet getWalletByUser(String userId);
	


}

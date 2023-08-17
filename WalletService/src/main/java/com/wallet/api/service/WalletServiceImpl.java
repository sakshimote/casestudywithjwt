package com.wallet.api.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.Order;
import com.wallet.api.model.Statement;
import com.wallet.api.model.OrderPaymentStatement;
import com.wallet.api.model.UserProfile;
import com.wallet.api.repository.EWalletRepository;
import com.wallet.api.repository.OrderPaymentStatementRepository;
import com.wallet.api.repository.StatementsRepository;


@Service
public class WalletServiceImpl implements EWalletService{
	
	@Autowired
	private EWalletRepository walletRepo;

	
	@Autowired
	private StatementsRepository statementRepo;
	
	@Autowired
	private OrderPaymentStatementRepository orderPaymentStatementRepository;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	// add new Wallet
	
	public EWallet addWallet(EWallet ewallet,String userId) {
		   
		UserProfile profile=restTemplate.getForObject("http://localhost:8095/api/user/"+userId, UserProfile.class);
	     Statement statement=new Statement();
	     
	     if(ewallet.getCurrentBalance()>10000) {
	    	 ewallet.setCurrentBalance(10000D);
	    	
		     statement.setAmount(10000D);
		    statement.setDate(LocalDate.now());
		    
		    
		     statement.setTransactionType("DEPOSIT");
		
		     
		List<Statement> statements=new ArrayList<>();
		statements.add(statement);
		     statementRepo.save(statement);
		     
		     ewallet.setStatements(statements);
		     ewallet.setUserId(profile.getId());
		     ewallet.setUsername(profile.getUserName());
		     statement.setTransactionRemarks(profile.getUserName()+ " Activated wallet account with initial amount of  "+ewallet.getCurrentBalance()+", Not with given amount, as limit for initial amount is only 10000" +" Also at date "+statement.getDate()+" By transaction type"
			     		+ " "+statement.getTransactionType()+"  Now, Current balance in wallet is "+ewallet.getCurrentBalance());
			return walletRepo.save(ewallet);
	     }else {
	    	 ewallet.setCurrentBalance(ewallet.getCurrentBalance());
	 	    Double initialAmount=ewallet.getCurrentBalance();
		     statement.setAmount(initialAmount);
		    statement.setDate(LocalDate.now());
		    
		    
		     statement.setTransactionType("DEPOSIT");
		     
		List<Statement> statements=new ArrayList<>();
		statements.add(statement);
		     statementRepo.save(statement);
		     
		     ewallet.setStatements(statements);
		     ewallet.setUserId(profile.getId());
		     ewallet.setUsername(profile.getUserName());
		     statement.setTransactionRemarks(profile.getUserName()+" Activated wallet account with initial amount of  "+ewallet.getCurrentBalance() +" at date "+statement.getDate()+" By transaction type"
			     		+ " "+statement.getTransactionType()+"  Now, Current balance in wallet is "+ewallet.getCurrentBalance());
			     
			return walletRepo.save(ewallet);
	     }
	     

	}
	
	//get list of all wallets
	public List<EWallet> getWallets(){
		return walletRepo.findAll();
	}
	
	
	//get wallet by id
	public EWallet getById(String walletId) {
		Optional<EWallet> ewallets=walletRepo.findById(walletId);
		return ewallets.get();
	}
	
	//add money
	public EWallet addMoney(String walletId,Double amount) {
	     Optional<EWallet> wallet=walletRepo.findById(walletId);
	     EWallet wallet2=wallet.get();
	 
	 
	    	  wallet2.setCurrentBalance(wallet2.getCurrentBalance()+amount);
	 	     Statement statement=new Statement();
	 	    OrderPaymentStatement orderPaymentStatement=new OrderPaymentStatement();
	    	 orderPaymentStatement.setAmount(amount);
	    	 orderPaymentStatement.setDate(LocalDate.now());
	    	 
	    	 orderPaymentStatement.setTransactionType("DEPOSIT");
	    	 orderPaymentStatement.setTransactionRemarks("added money");
		    	// orderPaymentStatement.setEwallet(wallet2);
		    	 orderPaymentStatement.setWalletId(walletId);
		    	 orderPaymentStatementRepository.save(orderPaymentStatement);
	    	 
	    	 
		     statement.setAmount(amount);
		    statement.setDate(LocalDate.now());
		    
		    
		     statement.setTransactionType("DEPOSIT");
		     statement.setTransactionRemarks(wallet2.getUsername()+" added "+amount+" at date and time  "+statement.getDate()+" By transaction type"
		     		+ " "+statement.getTransactionType()+"  Now, Current balance in wallet is "+wallet2.getCurrentBalance());
		     
		
		     statementRepo.save(statement);
		     
		 	List<Statement> statements=wallet2.getStatements();
		 	statements.add(statement);
		     
		     wallet2.setStatements(statements);
		    
		      walletRepo.save(wallet2);
		      return wallet2;
	      
	     
	}
	
	//pay money using e-wallet
	public OrderPaymentStatement payUsingWallet(Double amount,String walletId) {
	
		Double amountToPay=amount;
		 Optional<EWallet> wallet=walletRepo.findById(walletId);
	     EWallet wallet2=wallet.get();
	   
	 
	        if(amountToPay<wallet2.getCurrentBalance()) {
	    	 wallet2.setCurrentBalance(wallet2.getCurrentBalance()-amountToPay);
	    	 OrderPaymentStatement orderPaymentStatement=new OrderPaymentStatement();
	    	 orderPaymentStatement.setAmount(amountToPay);
	    	 orderPaymentStatement.setDate(LocalDate.now());
	    	 
	    	 orderPaymentStatement.setTransactionType("WITHDRAW");
	    	 orderPaymentStatement.setTransactionRemarks(wallet2.getUsername()+" Payed "+amountToPay
	    	 		+ "using ewallet at date "+orderPaymentStatement.getDate()+" By transaction type "+orderPaymentStatement.getTransactionType()+
	    	 		" Now current balance in account is "+wallet2.getCurrentBalance());
	    //	 orderPaymentStatement.setEwallet(wallet2);
	    	 orderPaymentStatement.setWalletId(walletId);
	    orderPaymentStatementRepository.save(orderPaymentStatement);
	    	 walletRepo.save(wallet2);
	     return orderPaymentStatement ; 
	    	 	    	 
	     }
	        else {
	    	 OrderPaymentStatement orderPaymentStatement=new OrderPaymentStatement();
	    	 orderPaymentStatement.setAmount(amountToPay);
	    	 orderPaymentStatement.setDate(LocalDate.now());
	    
	    	 orderPaymentStatement.setTransactionType("WITHDRAW");
	    	 orderPaymentStatement.setTransactionRemarks(wallet2.getUsername()+" cannot pay "+amountToPay+ " for order id"
	    	 		+ "using ewallet at date "+orderPaymentStatement.getDate()+" By transaction type "+orderPaymentStatement.getTransactionType()+
	    	 		" Because current balance is lower than amount to pay. Please Add money to wallet for payment. Current balance in account is "+wallet2.getCurrentBalance());
	    	// orderPaymentStatement.setEwallet(wallet2);
	    	 orderPaymentStatement.setWalletId(walletId);
	    	 orderPaymentStatementRepository.save(orderPaymentStatement);
	    	 walletRepo.save(wallet2);
	     return orderPaymentStatement; 
	     }
	
	}
	
	public List<OrderPaymentStatement> getAllPaymentHistory(){
		return orderPaymentStatementRepository.findAll();
	}
	
	
	//get all statements
	public List<Statement> getStatements(){
		return statementRepo.findAll();
	}
	
	
	
	//delete wallet by id
	public String deleteById(String walletId) {
		 walletRepo.deleteById(walletId);
		 return "deleted successfully";
			
	}

	@Override
	public EWallet removeMoney(String walletId, Double amount) {
		  Optional<EWallet> wallet=walletRepo.findById(walletId);
		     EWallet wallet2=wallet.get();
		     
		     if(wallet2.getCurrentBalance()<amount) {
		    	 wallet2.setCurrentBalance(wallet2.getCurrentBalance());
		    	   Statement statement=new Statement();
			 	    
				     statement.setAmount(0d);
				    statement.setDate(LocalDate.now());
		
				     statement.setTransactionType("DEPOSIT");
				     statement.setTransactionRemarks(wallet2.getUsername()+" cannot remove "+amount+" at date and time  "+statement.getDate()+" By transaction type"
				     		+ " "+statement.getTransactionType()+". Amount exceeds than current balance. Current balance in wallet is "+wallet2.getCurrentBalance());
				     
				
				     statementRepo.save(statement);
				     
				 	List<Statement> statements=wallet2.getStatements();
				 	statements.add(statement);
				     
				     wallet2.setStatements(statements);
				    
				      walletRepo.save(wallet2);
				      return wallet2;
		     }else {
		    	 wallet2.setCurrentBalance(wallet2.getCurrentBalance()-amount);
		    	  Statement statement=new Statement();
				    
				     statement.setAmount(amount);
				    statement.setDate(LocalDate.now());
				    
				    
				     statement.setTransactionType("WITHDRAW");
				     statement.setTransactionRemarks(wallet2.getUsername()+" removed "+amount+" at date and time  "+statement.getDate()+" By transaction type"
				     		+ " "+statement.getTransactionType()+"  Now, Current balance in wallet is "+wallet2.getCurrentBalance());
				     
				
				     statementRepo.save(statement);
				     
					 	List<Statement> statements=wallet2.getStatements();
					 	statements.add(statement);
					     
					     wallet2.setStatements(statements);
					    
				      walletRepo.save(wallet2);
				      return wallet2;
		     }
		       
		   
	}
	
    public EWallet getWalletByUser(String userId) {
   	 return walletRepo.findByUserId(userId);
    }
    
    public List<OrderPaymentStatement> getPaymentsHistory(String walletId) {
    	return orderPaymentStatementRepository.findByWalletId(walletId);
    }

	

}

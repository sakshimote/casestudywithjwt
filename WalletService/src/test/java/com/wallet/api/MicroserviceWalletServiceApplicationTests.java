package com.wallet.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import com.wallet.api.controller.EWalletController;
import com.wallet.api.model.EWallet;
import com.wallet.api.model.Statement;
import com.wallet.api.model.OrderPaymentStatement;
import com.wallet.api.service.WalletServiceImpl;

@SpringBootTest
class MicroserviceWalletServiceApplicationTests {
	
	@InjectMocks
	EWalletController ewalletController;
	
	@Mock
	WalletServiceImpl walletServiceImpl;
	
	EWallet ewallet;
	Statement statement;
	OrderPaymentStatement dto;
	
	List<EWallet> walletList;
	List<Statement> statementList;
	

	private final String walletId = "idw1";
	private final Double amountToBeAdded=500D;
	private final Double amountToBeRemoved=500D;
	private final Double amountToBePayed=500D;
	private final String username="ross";
	private final String userId="user11";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		statement=new Statement();
		dto=new OrderPaymentStatement();
		dto.setTransactionRemarks("Transaction remarks for paying money");
		statement.setAmount(200D);
		statement.setDate(LocalDate.of(2005, 06, 25));
	
		statement.setStatementId("id1");
		statement.setTransactionRemarks("transaction remarks");
		statement.setTransactionType("DEPOSIT/WITHDRAW");
		
		ewallet=new EWallet();
		ewallet.setCurrentBalance(10000D);
	    ewallet.setUserId(userId);
	    ewallet.setUsername(username);
		ewallet.setWalletId(walletId);
		
		walletList = new ArrayList<EWallet>();
		walletList.add(ewallet);
		statementList=new ArrayList<Statement>();
		
		statementList.add(statement);
		ewallet.setStatements(statementList);
	
		
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void addWallet() {
		// first check that all the required parameters are in the profile class
		assertNotNull(ewallet,"ewallet is null");
		assertNotNull(ewallet.getCurrentBalance(),"Cuurent balance is required");
		assertNotNull(ewallet.getStatements(),"statement details required");
		assertNotNull(ewallet.getUserId(),"userId is required");
		assertNotNull(ewallet.getUsername(),"user name is required");
		assertNotNull(ewallet.getWalletId(),"wallet id is required");
		//get the userProfile by when then method
		when(walletServiceImpl.addWallet(ewallet,userId)).thenReturn(ewallet);
		
		EWallet walletRest = ewalletController.addWallet(ewallet,userId);
		
		assertNotNull(walletRest);
		
		assertEquals(ewallet, walletRest);
		assertEquals(ewallet.getCurrentBalance(), walletRest.getCurrentBalance());
		assertEquals(ewallet.getStatements(), walletRest.getStatements());
		assertEquals(ewallet.getUserId(), walletRest.getUserId());
		assertEquals(ewallet.getUsername(), walletRest.getUsername());
		assertEquals(ewallet.getWalletId(), walletRest.getWalletId());
	}
	

	@Test
	 void getAllWallet() {
		
		when(walletServiceImpl.getWallets()).thenReturn(walletList);//mocking
		assertEquals(1, walletServiceImpl.getWallets().size()); 
	
	}
	

	@Test
	void getById() {
		when(walletServiceImpl.getById(walletId)).thenReturn(ewallet);
		
		EWallet walletRest = walletServiceImpl.getById(walletId);
		
		assertNotNull(walletRest,"User not available in DB");
		
		assertEquals(ewallet.getWalletId(), walletRest.getWalletId());
		
	}
	
	@Test
	void getStatements() {
		when(walletServiceImpl.getStatements()).thenReturn(statementList);
		assertEquals(1, walletServiceImpl.getStatements().size());
	}
	
	@Test
	void deleteById() {
        when(walletServiceImpl.deleteById(walletId)).thenReturn("Deleted Succesfully");
		
		when(walletServiceImpl.getById(walletId)).thenReturn(ewallet);

		String delete = ewalletController.deleteById(walletId);
		
		assertEquals("Deleted Succesfully", delete,"user not available in db");
		
	}
	
	@Test
	void addMoney() {
		
		when(walletServiceImpl.getById(walletId)).thenReturn(ewallet);
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()+amountToBeAdded);
		
		 Statement statement1=new Statement();
		statement1.setAmount(amountToBeAdded);
		statement1.setDate(LocalDate.of(2008, 06, 25));
	
		statement1.setStatementId("id2");
		statement1.setTransactionRemarks("transaction remarks for adding money");
		statement1.setTransactionType("DEPOSIT");
		statementList.add(statement1);
		ewallet.setStatements(statementList);
		when(walletServiceImpl.addMoney(walletId, amountToBeAdded)).thenReturn(ewallet);

		EWallet walletRest2 = ewalletController.addMoney(walletId,amountToBeAdded);

		assertNotNull(walletRest2);
		
		assertEquals(ewallet.getCurrentBalance(), walletRest2.getCurrentBalance());
		assertEquals(ewallet.getStatements(), walletRest2.getStatements());
		
		
	}
	
	@Test
	void removeMoney() {
		
		when(walletServiceImpl.getById(walletId)).thenReturn(ewallet);
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()-amountToBeRemoved);
		
		 Statement statement1=new Statement();
		statement1.setAmount(amountToBeRemoved);
		statement1.setDate(LocalDate.of(2008, 06, 25));
		
		statement1.setStatementId("id2");
		statement1.setTransactionRemarks("transaction remarks for removing money");
		statement1.setTransactionType("Withdraw");
		statementList.add(statement1);
		ewallet.setStatements(statementList);
		when(walletServiceImpl.addMoney(walletId, amountToBeAdded)).thenReturn(ewallet);

		EWallet walletRest2 = ewalletController.addMoney(walletId,amountToBeAdded);

		assertNotNull(walletRest2);
		
		assertEquals(ewallet.getCurrentBalance(), walletRest2.getCurrentBalance());
		assertEquals(ewallet.getStatements(), walletRest2.getStatements());
		
		
	}
	@Test
	void payUsingWallet() {
		
		when(walletServiceImpl.getById(walletId)).thenReturn(ewallet);
		ewallet.setCurrentBalance(ewallet.getCurrentBalance()-amountToBePayed);

		 OrderPaymentStatement statement1=new OrderPaymentStatement();
		statement1.setAmount(amountToBeRemoved);
		statement1.setDate(LocalDate.of(2008, 06, 25));
	
		statement1.setTransactionRemarks("Transaction remarks for paying money");
		statement1.setTransactionType("Withdraw");
	     when(walletServiceImpl.payUsingWallet(amountToBeRemoved, walletId)).thenReturn(dto);
		dto = ewalletController.payUsingWallet(amountToBeRemoved,walletId);
		

		assertNotNull(dto);
		assertEquals(statement1.getTransactionRemarks(), dto.getTransactionRemarks());
		
	
		
	}
	
}

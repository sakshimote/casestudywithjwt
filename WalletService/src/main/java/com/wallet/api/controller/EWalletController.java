package com.wallet.api.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.razorpay.RazorpayClient;
import com.wallet.api.model.AuthenticationRequest;
import com.wallet.api.model.AuthenticationResponse;
import com.wallet.api.model.EWallet;
import com.wallet.api.model.Order;
import com.wallet.api.model.Statement;
import com.wallet.api.model.OrderPaymentStatement;
import com.wallet.api.repository.EWalletRepository;
import com.wallet.api.repository.StatementsRepository;
import com.wallet.api.service.EWalletService;
import com.wallet.api.service.MyUserDetails;
import com.wallet.api.service.WalletServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class EWalletController  {
	
	@Autowired
	private RestTemplate restTemplate;


	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetails userDetailsService;
	
	@Autowired
	private com.wallet.api.JwtUtil jwtTokeUtil;
	
	org.slf4j.Logger logger=LoggerFactory.getLogger(EWalletController.class);
	
	@Autowired
	private WalletServiceImpl walletServiceImpl;
	

	@PostMapping("/authenticate")
	@ApiOperation("autyenticate method accessed")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
		try { authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}
		
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(userDetails);
		System.out.println("hii");
		final String jwt=jwtTokeUtil.generateToken(userDetails);
	
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	
	@GetMapping("/wallets")
	public List<EWallet> getWallets() {
		// TODO Auto-generated method stub
		logger.trace("get wallets method accessed");
		return walletServiceImpl.getWallets();
	}


	@PostMapping("/wallet/{userId}")
	public EWallet addWallet(@RequestBody EWallet ewallet,@PathVariable("userId") String userId) {
		// TODO Auto-generated method stub
		logger.trace("add wallet method accessed");
		return walletServiceImpl.addWallet(ewallet,userId);
	}


	
	@GetMapping("/wallets/{walletId}")
	public EWallet getById(@PathVariable("walletId") String walletId) {
		// TODO Auto-generated method stub
		logger.trace("get wallet by walletId method accessed");
		return walletServiceImpl.getById(walletId);
	}


	@GetMapping("/statements")
	public List<Statement> getStatements() {
		// TODO Auto-generated method stub
		logger.trace("get statements method accessed");
		return walletServiceImpl.getStatements();
	}

	
	@DeleteMapping("/wallet/{walletId}")
	public String deleteById( @PathVariable("walletId") String walletId) {
		logger.trace("delete wallet by id method accessed");
		 return walletServiceImpl.deleteById(walletId);
		
	}

	
	
	@PostMapping("/add/wallet/{walletId}/{amount}")
	public EWallet addMoney(@PathVariable("walletId") String walletId,@PathVariable("amount") Double amount) {
		// TODO Auto-generated method stub
		logger.trace("add money to wallet method accessed");
		 return walletServiceImpl.addMoney(walletId, amount);
	}
	
	
	@PostMapping("/remove/wallet/{walletId}/{amount}")
	public EWallet removeMoney(@PathVariable("walletId") String walletId,@PathVariable("amount") Double amount) {
		// TODO Auto-generated method stub
		logger.trace("remove money from wallet method accessed");
		 return walletServiceImpl.removeMoney(walletId, amount);
	}
	
	@GetMapping("/pay/byWallet/{amount}/{walletId}")
	public OrderPaymentStatement   payUsingWallet(@PathVariable("amount")Double amount,@PathVariable("walletId")  String walletId) {
		return walletServiceImpl.payUsingWallet(amount, walletId);
	}
	
	@GetMapping("/payments")
	public List<OrderPaymentStatement> getAllPaymentHistory(){
		return walletServiceImpl.getAllPaymentHistory();
	}
	
	
	
	@GetMapping("/wallet/user/{userId}")
	public EWallet getWalletByUser(@PathVariable("userId")String userId) {
		return walletServiceImpl.getWalletByUser(userId);
	}
	@GetMapping("/payment/history/{walletId}")
	 public List<OrderPaymentStatement> getPaymentsHistory(@PathVariable("walletId")String walletId){
		 return walletServiceImpl.getPaymentsHistory(walletId);
	 }
	
}

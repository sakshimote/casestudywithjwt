package com.wallet.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wallet.api.model.EWallet;
import com.wallet.api.model.OrderPaymentStatement;

public interface OrderPaymentStatementRepository extends MongoRepository<OrderPaymentStatement,String>{

	List<OrderPaymentStatement> findByWalletId(String walletId);

	

}

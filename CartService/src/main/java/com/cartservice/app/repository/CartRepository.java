package com.cartservice.app.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.cartservice.app.model.Cart;


public interface CartRepository extends MongoRepository<Cart,String>  {

	Optional<Cart> findByUserId(String userId);
	

}

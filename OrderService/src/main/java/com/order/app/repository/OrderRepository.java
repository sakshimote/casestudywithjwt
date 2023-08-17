package com.order.app.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.order.app.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>{

	List<Order> findByUserId(String userId);

}

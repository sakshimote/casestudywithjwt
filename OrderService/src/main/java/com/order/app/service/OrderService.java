package com.order.app.service;

import java.util.List;

import com.order.app.model.Address;
import com.order.app.model.Order;

public interface OrderService {


	public List<Order> getAllOrder();

	public Order getOrderById(String orderId);

	public String deleteOrderById(String OrderId);

	public Order addOrder(String cartId, Order order);

	public List<Order> getOrdersByUserId(String userId);

	public Order cancelOrderByOrderId(String orderId);

	
	
	
	
}

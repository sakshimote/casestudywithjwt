package com.wallet.api.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Document
public class Order {

	@Id
	private String orderId;

	@Field
	private LocalDate orderDate;

	

@Field
	private Double amountPaid;

@Field
	private String modeOfPayment;
@Field
	private List<ItemsAddedInCart> items;

	@Field
	private String orderStatus;

	@Field
	private Address address;
	@Field
	private String username;
	@Field
	private String userId;

	

	public Order(String orderId, LocalDate orderDate, Double amountPaid, String modeOfPayment,
			List<ItemsAddedInCart> items, String orderStatus, Address address, String username, String userId) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amountPaid = amountPaid;
		this.modeOfPayment = modeOfPayment;
		this.items = items;
		this.orderStatus = orderStatus;
		this.address = address;
		this.username = username;
		this.userId = userId;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ItemsAddedInCart> getItems() {
		return items;
	}

	public void setItems(List<ItemsAddedInCart> items) {
		this.items = items;
	}

	

	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	


	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
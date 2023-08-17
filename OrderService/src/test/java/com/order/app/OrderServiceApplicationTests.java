package com.order.app;

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

import com.order.app.controller.OrderController;
import com.order.app.model.Address;
import com.order.app.model.Cart;
import com.order.app.model.ItemsAddedInCart;
import com.order.app.model.Order;
import com.order.app.service.OrderServiceImpl;

@SpringBootTest
class OrderServiceApplicationTests {


	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderServiceImpl orderServiceImpl;
	
	
	Order order;
	ItemsAddedInCart items;
	List<ItemsAddedInCart> itemsList;
	List<Order> orders;
	Address address;
	Cart cart;
	
	private final String orderId="order11";
	private final LocalDate orderDate=LocalDate.now();
	private final Double amountPaid=500D;
	private final String modeOfPayemnt="payment gateway";
	private final String orderStatus="Order placed";
	private final String username="ross";
	private final String userId="user234";
	
	private final String cartId="cart12";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		order=new Order();
		address=new Address();
		
		cart=new Cart();
		items=new ItemsAddedInCart();
		orders=new ArrayList<Order>();
		itemsList=new ArrayList<>();
		items.setProductName("charger");
		items.setQuantity(1);
		itemsList.add(items);
		order.setItems(itemsList);
		order.setAddress(address);
		order.setAmountPaid(amountPaid);
		order.setModeOfPayment(modeOfPayemnt);
		order.setUsername(username);
		order.setOrderDate(orderDate);
		order.setOrderId(orderId);
		order.setOrderStatus(orderStatus);
		order.setUserId(userId);
		orders.add(order);
	
	}
	

	@Test
	void addOrderNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(order, "order is null");
		
	}
	
	@Test
	void addOrderOrderIdNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(order.getOrderId(), "order id is required ");
		
	}
	
	@Test
	void addOrderItemsNotNull() {
		
		assertNotNull(order.getItems(),"cart items required");
	}
	
	@Test
	void addOrderPriceNotNull() {
		
		assertNotNull(order.getAmountPaid(),"Amount to pay is required");
	}
	
	@Test
	void addOrderAddressNotNull() {
		
		assertNotNull(order.getAddress(),"Address is required");
	}
	
	@Test
	void addOrderModeOfPaymentNotNull() {
		
		assertNotNull(order.getModeOfPayment(),"mode of payment is required");
	}
	
	@Test
	void addOrderUsernameNotNull() {
		
		assertNotNull(order.getUsername(),"username is required");
	}
	
	@Test
	void addOrderOrderDateNotNull() {
		
		assertNotNull(order.getOrderDate(),"order date is required");
	}
	
	@Test
	void addOrderOrderStatusNotNull() {
		
		assertNotNull(order.getOrderStatus(),"order status is required");
	}
	
	@Test
	void addOrderUserIdNotNull() {
		
		assertNotNull(order.getUserId(),"user id is required");
	}
	@Test
	void addOrderUserNameNotNull() {
		
		assertNotNull(order.getUsername(),"user name is required");
	}
	@Test
	void addOrderCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order, orderRest);
	}
	@Test
	void addOrderOrderIdCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getOrderId(), orderRest.getOrderId());
	}
	
	@Test
	void addOrderOrderDateCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getOrderDate(), orderRest.getOrderDate());
	}
	@Test
	void addOrderUserIdCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getUserId(), orderRest.getUserId());
	}
	@Test
	void addOrderUserNameCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getUsername(), orderRest.getUsername());
	}
	@Test
	void addOrderItemsCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getItems(), orderRest.getItems());
	}
	@Test
	void addOrderModeOfPaymentCheck() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getModeOfPayment(), orderRest.getModeOfPayment());
	}
	@Test
	void addOrderAddress() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getAddress(), orderRest.getAddress());
	}
	@Test
	void addOrderTotalPrice() {
		when(orderServiceImpl.addOrder(cartId, order)).thenReturn(order);
		Order orderRest=orderController.addOrder(cartId, order);
		assertEquals(order.getAmountPaid(), orderRest.getAmountPaid());
	}
	@Test
	void getAllOrder() {
		when(orderServiceImpl.getAllOrder()).thenReturn(orders);
		orders=orderController.getAllOrder();
		assertEquals(1, orders.size() );
	}
	@Test
	void getOrderById() {
		when(orderServiceImpl.getOrderById(orderId)).thenReturn(order);
		Order orderRest=orderController.getOrderById(orderId);
		assertEquals(order.getOrderId(), orderRest.getOrderId());
	}
	
	@Test
	void getOrderByUserId() {
		when(orderServiceImpl.getOrdersByUserId(userId)).thenReturn(orders);
		orders=orderController.getOrderByUserId(userId);
		assertEquals(1, orders.size());
	}
	@Test
	void deleteOrderById() {
		when(orderServiceImpl.deleteOrderById(orderId)).thenReturn("Successfully deleted");
		when(orderServiceImpl.getOrderById(orderId)).thenReturn(order);
		String delete=orderController.deleteOrderById(orderId);
		assertEquals("Successfully deleted", delete ,"order not avalable in db");
	}
	@Test
	void cancelOrderByOrderId() {
		when(orderServiceImpl.cancelOrderByOrderId(orderId)).thenReturn(order);
		Order orderRest=orderController.cancelOrderByOrderId(orderId);
		order.setOrderStatus("cancelled");
		orderRest.setOrderStatus("cancelled");
		assertEquals(order.getOrderStatus(), orderRest.getOrderStatus());
	}
	
	@Test
	void contextLoads() {
	}
	
}

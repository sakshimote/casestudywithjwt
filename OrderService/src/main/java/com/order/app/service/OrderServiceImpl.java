package com.order.app.service;


import java.io.File;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.app.model.Cart;
import com.order.app.model.EmailDetails;
import com.order.app.model.Order;
import com.order.app.model.UserProfile;
import com.order.app.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OrderRepository orderRepository;
	
@Autowired
private JavaMailSender javaMailSender;


@Value("${spring.mail.username}") 
private String sender;
	
    
    public Order addOrder(String userId,Order order) {
		
		Cart cart = restTemplate.getForObject("http://localhost:8097/cart/byUser/"+userId, Cart.class);
		
		
		UserProfile profile = restTemplate.getForObject("http://localhost:8095/api/user/"+userId,
				UserProfile.class);
	
		
		order.setItems(cart.getProductsAdded());
		order.setAddress(profile.getAddress());
		order.setAmountPaid(cart.getTotalPrice());
		order.setUserId(userId);
		order.setUsername(profile.getUserName());
		order.setOrderDate(LocalDate.now());
		
		order.setOrderStatus("Order Placed");
		String msgBody="Hello "+profile.getFullName()+" Thank you for ordering on Shopy.in.\n We hope to see you again soon!!!.\n "
				+ "Please visit My orders on Shopy.in to see order details.";
		String subject="Your Shopy.in order of "+order.getItems().size()+" item has been dispatched";
		EmailDetails emailDetails=new EmailDetails(profile.getEmail(),msgBody,subject,"C:\\Users\\Admin\\Downloads\\shopy.png");
		sendMailWithAttachment(emailDetails);
		//sendSimpleMail(emailDetails);
		return orderRepository.save(order);
	
	
	}

	// get all Order
	public List<Order> getAllOrder() {
		return orderRepository.findAll();
	}

	// get Order by order id
	public Order getOrderById(String orderId) {
	
		
	   Order order= orderRepository.findById(orderId).get();
	
	   return order;
	   
	}
	
	
  // delete Order by id
	public String deleteOrderById(String orderid) {
		orderRepository.deleteById(orderid);
		return "Order Deleted  or Cancled Succesfully";
	}

	// get order by customer ID
	public List<Order> getOrdersByUserId(String UserId) {
		List<Order> orders = orderRepository.findByUserId(UserId);
		Collections.reverse(orders);
		return orders;

	}

	@Override
	public Order cancelOrderByOrderId(String orderId) {
		Order order = orderRepository.findById(orderId).get();
		order.setOrderStatus("Cancelled");
		return orderRepository.save(order);
		
	}
	
	 public String sendSimpleMail(EmailDetails details)
	    {
	 
	        // Try block to check for exceptions
	        try {
	 
	            // Creating a simple mail message
	            SimpleMailMessage mailMessage
	                = new SimpleMailMessage();
	 
	            // Setting up necessary details
	            mailMessage.setFrom(sender);
	            mailMessage.setTo(details.getRecipient());
	            mailMessage.setText(details.getMsgBody());
	            mailMessage.setSubject(details.getSubject());
	 
	            // Sending the mail
	            javaMailSender.send(mailMessage);
	            return "Mail Sent Successfully...";
	        }
	 
	        // Catch block to handle the exceptions
	        catch (Exception e) {
	            return "Error while Sending Mail";
	        }
	    }
	 
	    // Method 2
	    // To send an email with attachment
	    public String
	    sendMailWithAttachment(EmailDetails details)
	    {
	        // Creating a mime message
	        MimeMessage mimeMessage
	            = javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper;
	 
	        try {
	 
	            // Setting multipart as true for attachments to
	            // be send
	            mimeMessageHelper
	                = new MimeMessageHelper(mimeMessage, true);
	            mimeMessageHelper.setFrom(sender);
	            mimeMessageHelper.setTo(details.getRecipient());
	            mimeMessageHelper.setText(details.getMsgBody());
	            mimeMessageHelper.setSubject(
	                details.getSubject());
	 
	            // Adding the attachment
	            FileSystemResource file
	                = new FileSystemResource(
	                    new File(details.getAttachment()));
	 
	            mimeMessageHelper.addAttachment(
	                file.getFilename(), file);
	 
	            // Sending the mail
	            javaMailSender.send(mimeMessage);
	            return "Mail sent Successfully";
	        }
	 
	        // Catch block to handle MessagingException
	        catch (MessagingException e) {
	 
	            // Display message when exception occurred
	            return "Error while sending mail!!!";
	        }
	    }
	 
	

}

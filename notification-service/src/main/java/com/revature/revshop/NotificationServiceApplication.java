package com.revature.revshop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.revature.revshop.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Autowired
	private JavaMailSender mailSender;
	
	
		
	public void sendEmail(String to, String subject, String body) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("VibeVault@demomailtrap.com"); 
	    message.setTo(to);
	    message.setSubject(subject);
	    message.setText(body);
	    
	    try {
	        mailSender.send(message);
	        log.info("Email sent successfully to {}", to);
	    } catch (Exception e) {
	        log.error("Error sending email: {}", e.getMessage());
	        e.printStackTrace();
	    }
	}


	
	
	
	@KafkaListener(topics="notificationTopic", groupId = "notificationId")
	public void getOrderEvent(OrderEvent orderEvent) {
	    log.info("Received notification for order: {}", orderEvent.getOrderNumber());
	    
	    String subject = "VibeVault: Order Confirmation";
	    String body = "Thank you for shopping with VibeVault!\n\n"
	                + "We hope you are satisfied with our service.\n\n"
	                + "Your order ID is " + orderEvent.getOrderNumber() + ".\n\n"
	                + "We wish to hear back from you soon. Please don't contact us for return or replacement!";
	    
	    sendEmail("rushipasumarthi005@gmail.com", subject, body);
	}


	}




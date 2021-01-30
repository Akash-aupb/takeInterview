package com.sonu.msApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessageRemovedException;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@RestController
public class MsAppApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MsAppApplication.class);
	
	@Value("${spring.application.name}")
	private String name;

	@Autowired
	RestTemplate restTemplate; 
	   
	public static void main(String[] args) {
		logger.info("this is from main method");
		SpringApplication.run(MsAppApplication.class, args);
	}
	
	private void sendmail() throws IOException, MessagingException{
		Properties pro = new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(pro, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("akashkkr.kmr@gmail.com", "9852292295");
		      }
		   });
		

		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("akashkkr.kmr@gmail.com", false));
		   
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("akaskuma@gmail.com"));
		   msg.setSubject("Welcome to takeInterview");
		   msg.setContent("This is the POC", "text/html");
		   msg.setSentDate(new java.util.Date());
		   Transport.send(msg);
	}
	
	@RequestMapping(value="/")
	public String hello() throws AddressException, MessagingException, IOException {
		try {
			sendmail();
		} catch (IOException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("this is from hello  method , now calling send email function");
		
		return "Hello world";
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@RequestMapping(value="/name")
	public String getName() {
		logger.info("this is from getName  method");
		return name;
	}
	
	@RequestMapping(value="/settopic",method=RequestMethod.POST)
	public String createName(@RequestBody String topic) {
		logger.info("This is the name Entered"+name);
		System.out.print("THIS IS THE NAME"+name);
		return "CREATED";
	}

}

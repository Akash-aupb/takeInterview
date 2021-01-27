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
	
	@RequestMapping(value="/")
	public String hello() {
		logger.info("this is from hello  method");
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

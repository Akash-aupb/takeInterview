package com.sonu.msApp;

import org.springframework.stereotype.Service;

@Service
public class topicSeriveImp implements topicService{

	@Override
	public void createTopic(String name) {
		
		topic.setName(name);
		
	}

	
	
}

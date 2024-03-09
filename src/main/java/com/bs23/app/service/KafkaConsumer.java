package com.bs23.app.service;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "myTopic", groupId = "user-group")
public class KafkaConsumer {
	
	@KafkaHandler
	public void listen(String message) {
		System.out.println("Message: " + message);
	};

}

package com.learning.springbootkafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
	public void readMessage(String message) {
		logger.info("Message recceived from topic is :: {}", message);
	}
}

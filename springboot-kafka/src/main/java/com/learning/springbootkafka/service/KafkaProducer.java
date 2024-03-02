package com.learning.springbootkafka.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	@Value("${kafka.stringtopic}")
	String stringTopic;

	@Value("${kafka.jsontopic}")
	String jsonTopic;
	
	private KafkaTemplate<String, String> kafkaTemplate;

	private KafkaTemplate<String, JSONObject> kafkaJSONTemplate;
	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
			KafkaTemplate<String, JSONObject> kafkaJSONTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.kafkaJSONTemplate = kafkaJSONTemplate;
	}
	
	private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	
	public void sendMessage(String message) {
		kafkaTemplate.send(stringTopic,message);
		logger.info("message:{} sent to kafka topic:{}",message,stringTopic);
		
	}
	
	public void sendJSONMessage(JSONObject message) {

		Message<JSONObject> jsonMessage = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC,jsonTopic).build();
		kafkaJSONTemplate.send(jsonMessage);
		logger.info("message:{} sent to kafka topic:{}", message, jsonTopic);

	}
	
}

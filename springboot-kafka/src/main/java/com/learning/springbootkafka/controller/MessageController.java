package com.learning.springbootkafka.controller;

import org.apache.kafka.common.protocol.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootkafka.service.KafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {

	private KafkaProducer kafkaProducer;

	public MessageController(KafkaProducer kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}
	
	//http://localhost:8087/api/v1/kafka/publish?message=value
	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam(name = "message") String message){
		kafkaProducer.sendMessage(message);
		return new ResponseEntity<>("Sent",HttpStatus.OK);
	}
	
	
}

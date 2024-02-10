package com.learning.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Value("${kafka.topic}")
	String topic;
	
	@Bean
	NewTopic newTopic() {
		return TopicBuilder.name(topic).build();
	}
}

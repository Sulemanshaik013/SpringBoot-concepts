package com.learning.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${kafka.stringtopic}")
	String stringTopic;

	@Value("${kafka.jsontopic}")
	String jsonTopic;

	@Bean
	NewTopic stringTopic() {
		return TopicBuilder.name(stringTopic).build();
	}

	@Bean
	NewTopic jsonTopic() {
		return TopicBuilder.name(jsonTopic).build();
	}
}

package com.coding.spectrum.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${myapp.kafka.topic}")
	private String kafkaTopic;

	public void sendMessage(String message) {
		// Publish the message to the Kafka topic
        kafkaTemplate.send(kafkaTopic, message);
	}
}

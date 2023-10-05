package com.coding.spectrum.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Vikash
 *
 */
@Service
public class KafkaConsumerService {

	private final List<String> receivedMessages = new ArrayList<>();

	/**
	 * consume messages from kafka topic
	 * @param message
	 */
	@KafkaListener(topics = "${myapp.kafka.topic}")
	public void consumeMessage(String message) {
		System.out.println("Received message from Kafka: " + message);
		// Store the received message in the list
		receivedMessages.add(message);
	}

	/**
     * Get the list of received messages.
     *
     * @return A list of received messages.
     */
	public List<String> getReceivedMessages() {
		return receivedMessages;
	}
}

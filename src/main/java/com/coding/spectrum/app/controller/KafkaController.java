package com.coding.spectrum.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.spectrum.app.service.KafkaConsumerService;
import com.coding.spectrum.app.service.KafkaProducerService;

/**
 * 
 * @author Vikash
 *
 */
@RestController
@RequestMapping("/api")
public class KafkaController {

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private KafkaConsumerService kafkaConsumerService;

	/**
	 * Rest Endpoint to publish a message to Kafka.
	 *
	 * @param message The message to be published.
	 * @return A message indicating the result.
	 */
	@PostMapping("/publish")
	public ResponseEntity<String> publishToKafka(@RequestBody String message) {
		try {
			kafkaProducerService.sendMessage(message);
			return ResponseEntity.ok("Message published to Kafka successfully..");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to publish message to Kafka: " + e.getMessage());
		}
	}

	/**
	 * Endpoint to read messages from Kafka.
	 *
	 * @return A list of messages received from Kafka.
	 */
	@GetMapping("/read")
	public ResponseEntity<List<String>> readFromKafka() {
		List<String> messages = kafkaConsumerService.getReceivedMessages();
		return ResponseEntity.ok(messages);
	}

}

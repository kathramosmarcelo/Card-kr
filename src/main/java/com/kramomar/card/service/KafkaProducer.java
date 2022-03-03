package com.kramomar.card.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kramomar.card.Util.Topic;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendNumberAccountOrigin(String value) {
        kafkaTemplate.send(Topic.FIND_ACCOUNT_ORIGIN,value);
        logger.info("Messages successfully pushed on topic: " + Topic.FIND_ACCOUNT_ORIGIN);
    }
    public void sendNumberAccountDestination(String value) {
        kafkaTemplate.send(Topic.FIND_ACCOUNT_DESTINATION,value);
        logger.info("Messages successfully pushed on topic: " + Topic.FIND_ACCOUNT_DESTINATION);
    }
}

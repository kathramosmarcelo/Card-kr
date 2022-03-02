package com.kramomar.card.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class CardListener {
private final Logger logger = LoggerFactory.getLogger(CardListener.class);


@KafkaListener(topics = "nuevo-topic", groupId = "group_id")
public void consume(String message){
logger.info("Se ha consumido el mensaje " + message);
     }
}
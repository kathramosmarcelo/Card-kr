package com.kramomar.card.listener;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kramomar.card.Util.JsonUtils;
import com.kramomar.card.Util.Topic;
import com.kramomar.card.component.CardComponent;
import com.kramomar.card.service.KafkaProducer;


@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    private final CardComponent cardComponent;
    private final KafkaProducer kafkaProducer;

    @KafkaListener(topics = Topic.FIND_NUMBER_CARD_ORIGIN, groupId = "group_id")
    public void consumeCardOrigin(String param) {
        logger.info("Has been published find number card origin from service wallet-kr : " + param);
        sendMessageAccount(param, 0);

    }

    @KafkaListener(topics = Topic.FIND_NUMBER_CARD_DESTINATION, groupId = "group_id")
    public void consumeCardDestination(String param) {
        logger.info("Has been published find number card destination from service wallet-kr : " + param);
        sendMessageAccount(param, 1);

    }
    public void sendMessageAccount(String param, int index) {
        String newNumberCard = JsonUtils.removeFirstAndLast(param);
        var find = cardComponent.findByNumberCard(newNumberCard);
        find.doOnNext(p -> {
            if (index == 0) {
                kafkaProducer.sendNumberAccountOrigin(p.getNumberAccount());
            } else {
                kafkaProducer.sendNumberAccountDestination(p.getNumberAccount());
            }
            logger.info("send messages to account -->");
        }).subscribe();
    }

}

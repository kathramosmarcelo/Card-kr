package com.kramomar.card.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.kramomar.card.entity.Card;
import com.kramomar.card.repository.ICardRepository;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CardService {

    private final static Logger logger = LoggerFactory.getLogger(CardService.class);
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private  final ICardRepository iCardRepository;

    public Mono<Card> create(final Mono<Card> entityToDto) {
        return entityToDto
                .flatMap(iCardRepository::save);

    }
    public Mono<Card> findByNumberCard(String numberCard) {
        logger.info("inside methode find by number Card ");
        Query query = new Query();
        query.addCriteria(Criteria.where("numberCard").is(numberCard));
        return reactiveMongoTemplate.findOne(query, Card.class);

    }
}

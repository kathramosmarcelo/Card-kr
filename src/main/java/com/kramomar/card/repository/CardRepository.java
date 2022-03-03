package com.kramomar.card.repository;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;


import com.kramomar.card.entity.Card;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
@RequiredArgsConstructor
public class CardRepository {

    private static final Logger logger = LoggerFactory.getLogger(CardRepository.class);
    private static final String KEY = "Card";
    private final ReactiveRedisOperations<String, Card> redisOperations;
    private final ReactiveHashOperations<String, String, Card> hashOperations;

    @Autowired
    public CardRepository(ReactiveRedisOperations<String, Card> redisOperations) {
        this.redisOperations = redisOperations;
        this.hashOperations = redisOperations.opsForHash();
    }

    public Mono<Card> create(Card card) {
        logger.info("inside methode create");
        if (card.getId() != null) {
            String id = UUID.randomUUID().toString();
            card.setId(id);
        }
        return hashOperations.put(KEY, card.getId(), card)
                .map(isSaved -> card);
    }

    public Flux<Card> read() {
        return hashOperations.values(KEY);
    }

    public Mono<Card> update(Card card, String id) {
        Mono<Boolean> booleanMono = existsById(id);
        return booleanMono.flatMap(exist -> {
                    if (Boolean.TRUE.equals(exist)) {
                        return hashOperations.put(KEY, card.getId(), card)
                                .map(isSaved -> card);
                    } else {
                        return hashOperations.put(KEY, card.getId(), card)
                                .map(isSaved -> card);
                    }
                })
                .thenReturn(card);
    }

    public Mono<Void> delete(String id) {
        return hashOperations.remove(KEY, id).then();
    }

    public Mono<Card> findByNumberCard(String numberCard) {
        return hashOperations.values(KEY)
                .filter(p -> p.getNumberCard().equals(numberCard))
                .singleOrEmpty();
    }

    public Mono<Card> findById(String id) {
        return hashOperations.get(KEY, id);
    }

    public Mono<Boolean> existsById(String id) {
        return hashOperations.hasKey(KEY, id);
    }

}

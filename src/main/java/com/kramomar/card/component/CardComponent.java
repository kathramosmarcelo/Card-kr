package com.kramomar.card.component;

import org.springframework.stereotype.Component;

import com.kramomar.card.entity.Card;
import com.kramomar.card.repository.CardRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CardComponent {
    private final CardRepository cardRepository;

    public Mono<Card> create(Card card) {
        return cardRepository.create(card);
    }

    public Flux<Card> read() {
        return cardRepository.read();
    }

    public Mono<Card> update(Card card, String id) {
        return cardRepository.update(card, id);
    }

    public Mono<Void> delete(String id) {
        return cardRepository.delete(id);
    }

    public Mono<Card> findById(String id) {
        return cardRepository.findById(id);
    }

    public Mono<Card> findByNumberCard(String numberCard) {
        return cardRepository.findByNumberCard(numberCard);
    }
}
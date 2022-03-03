package com.kramomar.card.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kramomar.card.entity.Card;
import com.kramomar.card.service.CardService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {


    private  final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Card> create(
            @RequestBody final Mono<Card> cardDtoMono) {
        return cardService.create(cardDtoMono);
    }

    @GetMapping("/{numberCard}")
    @Cacheable(value = "Card", key = "numberCard")
    public Mono<Card> findByNumberCard(@PathVariable final String numberCard) {
        return cardService.findByNumberCard(numberCard);
    }

}

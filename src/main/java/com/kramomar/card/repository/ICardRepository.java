package com.kramomar.card.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.kramomar.card.entity.Card;

@Repository
public interface ICardRepository extends ReactiveMongoRepository<Card, String> {

}

package com.kramomar.card.repository;

import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;

import com.kramomar.card.entity.Card;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CardRepository {

	private final ReactiveRedisOperations<String, Card> redisOperations;
	  private final ReactiveHashOperations<String,String, Card> hashOperations;

	  public static final String KEY = "Card";
	  public CardRepository(ReactiveRedisOperations<String, Card> redisOperations) {
	    this.redisOperations = redisOperations;
	    this.hashOperations = redisOperations.opsForHash();
	  }
	  
	  public Flux<Card> findall(){
		  return hashOperations.values(KEY);
	  }
	
	  
	  public Mono<Card> save(Card card){
		return hashOperations.put(KEY, card.getId(), card).map(isSaved -> card);
	  }
	  
	
}

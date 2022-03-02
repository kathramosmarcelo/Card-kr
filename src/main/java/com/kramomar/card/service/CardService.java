package com.kramomar.card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kramomar.card.entity.Card;

@Service
public class CardService {
	@Autowired
	public  KafkaTemplate<String, Card> kafkaTemplate;
	/*
	
	public String getsms( String numberAccount) {
	    kafkaTemplate.send(NameTopic.NAMETOPIC, new Card("ACC-001",numberAccount, 12000L,new Date()));
	    return "Connecting Successfully :)";
	}

	public Accounts publishEventAccounts(Accounts accounts){
	    kafkaTemplate.send(NameTopic.NAMETOPIC,accounts);
	    return accounts;
	}
	*/
}

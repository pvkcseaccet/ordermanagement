package com.mediams.challenge.ordermanagement.akka.actorproducers;

import akka.actor.ActorSystem;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ActorSystemProducer
{
	@Bean
	public ActorSystem actorSystem()
	{
		return ActorSystem.create("FulfillmentActor");
	}
}

package com.mediams.challenge.ordermanagement.akka.actorproducers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ActorsProducer//Indirect Actor producer
{
	@Autowired
	private volatile ApplicationContext applicationContext;

	@Autowired
	private ActorSystem actorSystem;

	public ActorRef createActor(String beanName, String actorName)
	{
		Props props = Props.create(FulfillmentActorSystemProducer.class, applicationContext, beanName);
		return actorSystem.actorOf(props, actorName);
	}


}

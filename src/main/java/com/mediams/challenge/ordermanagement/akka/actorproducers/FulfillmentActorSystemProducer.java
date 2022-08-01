package com.mediams.challenge.ordermanagement.akka.actorproducers;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

import org.springframework.context.ApplicationContext;

public class FulfillmentActorSystemProducer implements IndirectActorProducer
{

	private ApplicationContext applicationContext;
	private String beanActorName;

	private FulfillmentActorSystemProducer(ApplicationContext applicationContext, String beanActorName)
	{
		this.applicationContext = applicationContext;
		this.beanActorName = beanActorName;
	}

	@Override public Actor produce()
	{
		return (Actor) applicationContext.getBean(this.beanActorName);
	}

	@Override public Class<? extends Actor> actorClass()
	{
		return (Class<? extends Actor>) applicationContext.getType(this.beanActorName);
	}
}

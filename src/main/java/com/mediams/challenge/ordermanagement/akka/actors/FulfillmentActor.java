package com.mediams.challenge.ordermanagement.akka.actors;

import akka.actor.UntypedAbstractActor;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;
import com.mediams.challenge.ordermanagement.services.OrderManagementService;

@Component("FulfillmentActor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FulfillmentActor extends UntypedAbstractActor
{
	public FulfillmentActor()
	{
	}

	@Override public void onReceive(Object message) throws Throwable
	{
		getSender().tell(((new SecureRandom().nextInt(100) & 1) == 1) ? FulfillmentStatus.SUCCESS : FulfillmentStatus.FAILED,  getSelf());
		getContext().system().stop(this.getSelf());
	}
}

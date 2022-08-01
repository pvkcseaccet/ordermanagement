package com.mediams.challenge.ordermanagement.services;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Awaitable;
import scala.concurrent.duration.FiniteDuration;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.mediams.challenge.ordermanagement.Constants.AkkaConstants;
import com.mediams.challenge.ordermanagement.Constants.OrderManagementConstants;
import com.mediams.challenge.ordermanagement.akka.actorproducers.ActorsProducer;
import com.mediams.challenge.ordermanagement.datatransferobjects.OrderDTO;
import com.mediams.challenge.ordermanagement.domainvalues.DeliveryStatus;
import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;
import com.mediams.challenge.ordermanagement.domainvalues.OrderStatus;
import com.mediams.challenge.ordermanagement.domainvalues.PaymentStatus;
import com.mediams.challenge.ordermanagement.exceptions.OrderManagementException;
import com.mediams.challenge.ordermanagement.repositories.OrderRepository;
import com.mediams.challenge.ordermanagement.exceptions.OrderNotFoundException;
import com.mediams.challenge.ordermanagement.domainobjects.Order;
import com.mediams.challenge.ordermanagement.transformers.OrderTransformer;
import com.mediams.challenge.ordermanagement.utils.ServicesUtil;

@Service
public class OrderManagementService
{

	@Autowired
	private OrderRepository orderManagementRepository;

	@Autowired
	private final ActorsProducer actorsProducer;


	@Autowired private OrderManagementService(OrderRepository orderManagementRepository, ActorsProducer actorsProducer)
	{
		this.orderManagementRepository = orderManagementRepository;
		this.actorsProducer = actorsProducer;
	}
	public OrderDTO createOrder(Order order) throws Exception
	{
		Order createdOrder = orderManagementRepository.save(order);
		return OrderTransformer.transformOrder(createdOrder);
	}

	public List<Order> getOrders() throws Exception
	{
		List<Order> orders = new LinkedList<>();
		orderManagementRepository.findAll().iterator().forEachRemaining(orders::add);
		return orders;
	}

	public Order getOrder(Long orderID) throws Exception
	{
		return orderManagementRepository.findById(orderID).orElseThrow(OrderNotFoundException::new);
	}

	public Order updateStatus(Long orderID, String statusName, Map<String, Object> request) throws Exception
	{
		if (request.isEmpty())
		{
			throw new OrderManagementException("Status is Found To be Empty.");
		}
		switch(statusName)
		{

			case OrderManagementConstants.URI.PAYMENT_STATUS:
			{
				PaymentStatus status = PaymentStatus.valueOf((String) request.get(OrderManagementConstants.MultipartRequestKeys.OrderStatusUpdate.STATUS));
				return this.updatePaymentStatus(orderID, status);
			}
			case OrderManagementConstants.URI.FULFILLMENT_STATUS:
			{
				ActorRef actorRef = actorsProducer.createActor(AkkaConstants.Beans.FULFILLMENT_ACTOR_BEAN, AkkaConstants.Actors.FULFILLMENT_ACTOR + orderID);
				FiniteDuration duration = new FiniteDuration(AkkaConstants.Timeouts.FULFILLMENT, TimeUnit.SECONDS);
				Timeout timeout = Timeout.durationToTimeout(duration);

				Awaitable<Object> fulfillmentFuture = Patterns.ask(actorRef, AkkaConstants.Messages.EMPTY, timeout);
				FulfillmentStatus fulfillmentStatus = Optional.of((FulfillmentStatus) Await.result(fulfillmentFuture, duration)).get();
				return this.updateFulfillmentStatus(orderID, fulfillmentStatus);
			}
			case OrderManagementConstants.URI.DELIVERY_STATUS:
			{
				DeliveryStatus status = DeliveryStatus.valueOf((String) request.get(OrderManagementConstants.MultipartRequestKeys.OrderStatusUpdate.STATUS));
				return this.updateDeliveryStatus(orderID, status);
			}

		}

		return null;
	}


	private Order updatePaymentStatus(Long orderID, PaymentStatus status) throws Exception
	{
		Order order = this.getOrder(orderID);

		ServicesUtil.Validators.doValidatePaymentStatusUpdate(order);

		order.setPaymentStatus(status);
		order.setFulfillmentStatus(FulfillmentStatus.PENDING);


		this.orderManagementRepository.save(order);
		return order;
	}

	private Order updateFulfillmentStatus(Long orderID, FulfillmentStatus status) throws Exception
	{
		Order order = this.getOrder(orderID);

		ServicesUtil.Validators.doValidateFulfillmentUpdate(order);

		order.setDeliveryStatus(FulfillmentStatus.SUCCESS == status ? DeliveryStatus.INITIATED : DeliveryStatus.NOT_INITIATED);
		order.setFulfillmentStatus(status);

		this.orderManagementRepository.save(order);

		return order;
	}

	private Order updateDeliveryStatus(Long orderID, DeliveryStatus status) throws Exception
	{
		Order order = this.getOrder(orderID);

		ServicesUtil.Validators.doValidateDeliveryUpdate(order);

		order.setOrderStatus(OrderStatus.CLOSED);
		order.setDeliveryStatus(status);

		this.orderManagementRepository.save(order);

		return order;
	}
}

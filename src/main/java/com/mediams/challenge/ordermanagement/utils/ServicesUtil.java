package com.mediams.challenge.ordermanagement.utils;

import com.mediams.challenge.ordermanagement.domainobjects.Order;
import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;
import com.mediams.challenge.ordermanagement.domainvalues.OrderStatus;
import com.mediams.challenge.ordermanagement.domainvalues.PaymentStatus;
import com.mediams.challenge.ordermanagement.exceptions.OrderManagementException;

public class ServicesUtil
{
	public static final class Validators
	{
		public static void doValidateFulfillmentUpdate(Order order) throws OrderManagementException
		{
			if (OrderStatus.CLOSED == order.getOrderStatus())
			{
				throw new OrderManagementException("Order has been already closed.");
			}

			if (PaymentStatus.PENDING == order.getPaymentStatus())
			{
				throw new OrderManagementException("Fulfillment can wait as Payment has not yet initiated.");
			}

		}

		public static void doValidatePaymentStatusUpdate(Order order) throws OrderManagementException
		{
			if (OrderStatus.CLOSED == order.getOrderStatus())
			{
				throw new OrderManagementException("Order has been already closed.");
			}

			if (PaymentStatus.SUCCESS == order.getPaymentStatus())
			{
				throw new OrderManagementException("Payment has been done for already paid order.");
			}

		}

		public static void doValidateDeliveryUpdate(Order order) throws OrderManagementException
		{
			if (PaymentStatus.FAILED == order.getPaymentStatus())
			{
				throw new OrderManagementException("Payment has Failed for this order.");
			}

			if (PaymentStatus.PENDING == order.getPaymentStatus())
			{
				throw new OrderManagementException("Payment yet to be made for this order.");
			}

			if (FulfillmentStatus.FAILED == order.getFulfillmentStatus())
			{
				throw new OrderManagementException("No Delivery has been initiated since fulfillment is marked failed for this order.");
			}
		}
	}

}

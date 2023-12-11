package com.mediams.challenge.ordermanagement.utils;

import com.mediams.challenge.ordermanagement.datatransferobjects.OrderDTO;
import com.mediams.challenge.ordermanagement.datatransferobjects.OrderLineItemDTO;
import com.mediams.challenge.ordermanagement.domainobjects.LineItems;
import com.mediams.challenge.ordermanagement.domainobjects.Order;
import com.mediams.challenge.ordermanagement.exceptions.OrderManagementException;

public class OrderUtil
{
	public static final class Validators
	{
		public static void validateOrder(Order order) throws OrderManagementException
		{

		}
	}

	public static Double computeOrderValue(Order order)
	{
		return order
			.getItems()
			.stream()
			.map(LineItems::getTotal)
			.reduce(Double::sum)
			.get();

	}

	public static Double computeOrderValue(OrderDTO orderDTO)
	{
		return orderDTO
			.getLineItems()
			.stream()
			.map(OrderLineItemDTO::getTotal)
			.reduce(Double::sum)
			.get();
	}
}

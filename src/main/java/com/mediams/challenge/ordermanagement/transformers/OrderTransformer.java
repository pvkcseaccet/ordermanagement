package com.mediams.challenge.ordermanagement.transformers;

import java.util.List;
import java.util.stream.Collectors;

import com.mediams.challenge.ordermanagement.datatransferobjects.OrderDTO;
import com.mediams.challenge.ordermanagement.domainobjects.Order;

public class OrderTransformer
{
	public static Order transformOrderDTO(OrderDTO orderDTO)
	{
		return new Order(orderDTO.getId(),
			orderDTO.getTotal(),
			orderDTO.getOrderStatus(),
			orderDTO.getPaymentStatus(),
			orderDTO.getDeliveryStatus(),
			orderDTO.getFulfillmentStatus(),
			LineItemTransformer.transformOrderLineItemDTOList(orderDTO.getLineItems()));
	}

	public static OrderDTO transformOrder(Order order)
	{
		return new OrderDTO(order.getID(),
			order.getTotal(),
			order.getOrderStatus(),
			order.getPaymentStatus(),
			order.getDeliveryStatus(),
			order.getFulfillmentStatus(),
			order.getItems());
	}

	public static List<OrderDTO> transformOrders(List<Order> orderList)
	{
		return orderList.stream()
			.map(OrderTransformer::transformOrder)
			.collect(Collectors.toList());
	}
}

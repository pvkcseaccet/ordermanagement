package com.mediams.challenge.ordermanagement.transformers;

import java.util.List;
import java.util.stream.Collectors;

import com.mediams.challenge.ordermanagement.datatransferobjects.OrderLineItemDTO;
import com.mediams.challenge.ordermanagement.domainobjects.LineItems;

public class LineItemTransformer
{
	public static LineItems transformOrderLineItemDTO(OrderLineItemDTO lineItemDTO)
	{
		return new LineItems(lineItemDTO.getItemID(),
			lineItemDTO.getQuantity(),
			lineItemDTO.getTotal(),
			lineItemDTO.getCreatedTime());
	}

	public static List<LineItems> transformOrderLineItemDTOList(List<OrderLineItemDTO> items)
	{
		return items
			.stream()
			.map(LineItemTransformer::transformOrderLineItemDTO)
			.collect(Collectors.toList());
	}

	public static OrderLineItemDTO transformOrder(LineItems order)
	{
		return new OrderLineItemDTO(order.getItemID(),
			order.getQuantity(),
			order.getTotal(),
			order.getCreatedTime());
	}

	public static List<OrderLineItemDTO> transformOrderLineItemList(List<LineItems> items)
	{
		return items
			.stream()
			.map(LineItemTransformer::transformOrder)
			.collect(Collectors.toList());
	}
}

package com.mediams.challenge.ordermanagement.datatransferobjects;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderLineItemDTO
{
	@JsonProperty("item_id") private Long itemID;

	private int quantity;

	private Double total;

	@JsonProperty("created_time") private ZonedDateTime createdTime = ZonedDateTime.now();

	public OrderLineItemDTO(Long itemID, int quantity, Double total)
	{
		this.itemID = itemID;
		this.quantity = quantity;
		this.total = total;
	}

	private void setItemID(Long itemID)
	{
		this.itemID = itemID;
	}

	private void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@Autowired
	private void setTotal(Double total)
	{
		this.total = total;
	}

	private OrderLineItemDTO() {}

	public OrderLineItemDTO(Long itemID, int quantity, Double total, ZonedDateTime createdTime)
	{
		this.itemID = itemID;
		this.quantity = quantity;
		this.total = total;
		this.createdTime = createdTime;
	}

	public Long getItemID()
	{
		return itemID;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public Double getTotal()
	{
		return total;
	}

	public ZonedDateTime getCreatedTime()
	{
		return createdTime;
	}
}

package com.mediams.challenge.ordermanagement.domainobjects;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class LineItems
{

	@Column(nullable = false)
	private Long itemID;

	@Column
	private int quantity;

	@Column(precision = 2, nullable = false, columnDefinition = "DECIMAL(7,2)")
	private Double total;


	@Column(updatable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime createdTime = ZonedDateTime.now();


	public LineItems(Long itemID, int quantity, Double total, ZonedDateTime createdTime)
	{
		this.itemID = itemID;
		this.quantity = quantity;
		this.total = total;
		this.createdTime = createdTime;
	}

	public LineItems() {}

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

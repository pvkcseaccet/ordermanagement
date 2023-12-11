package com.mediams.challenge.ordermanagement.domainobjects;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.mediams.challenge.ordermanagement.domainvalues.ItemStatus;
import com.mediams.challenge.ordermanagement.domainvalues.OrderStatus;

@Entity
@Table(name = "items")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, precision = 2, columnDefinition = "DECIMAL(7,2)")
	private BigDecimal price;

	@Enumerated(EnumType.STRING)
	@Column
	private ItemStatus itemStatus;

	@Column(updatable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime createdTime = ZonedDateTime.now();

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime updatedTime = ZonedDateTime.now(); //Will be modified

	public Long getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public ItemStatus getItemStatus()
	{
		return itemStatus;
	}

	public ZonedDateTime getCreatedTime()
	{
		return createdTime;
	}

	public ZonedDateTime getUpdatedTime()
	{
		return updatedTime;
	}
}

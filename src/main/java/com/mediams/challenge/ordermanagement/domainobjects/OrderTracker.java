package com.mediams.challenge.ordermanagement.domainobjects;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.mediams.challenge.ordermanagement.domainvalues.Status;

@Embeddable
public class OrderTracker
{
	@ManyToOne
	private Order order;

	@Column
	private String status;

	@Column(updatable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime createdTime = ZonedDateTime.now();

}

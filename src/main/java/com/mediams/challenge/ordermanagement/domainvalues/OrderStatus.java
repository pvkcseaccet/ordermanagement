package com.mediams.challenge.ordermanagement.domainvalues;

public enum OrderStatus implements Status
{
	CREATED,
	PAID,
	IN_FULFILLMENT,
	CLOSED;
}

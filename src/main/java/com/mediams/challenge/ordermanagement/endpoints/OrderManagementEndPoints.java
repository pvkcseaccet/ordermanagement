package com.mediams.challenge.ordermanagement.endpoints;

public class OrderManagementEndPoints
{
	public static final String UPDATE_FULFILLMENT_STATUS = "/{orderID}/status/fullfillment";
	public static final String UPFATE_STATUS = "/{orderID}/status/{status}";
	public static final String ORDERS = "/api/v1/orders";

	public static final String GET_ORDER = "/{orderID}";
}

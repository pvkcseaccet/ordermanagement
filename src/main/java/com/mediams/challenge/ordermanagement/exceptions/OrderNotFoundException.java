package com.mediams.challenge.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class OrderNotFoundException extends OrderManagementException
{

	public OrderNotFoundException()
	{
		super("Order Not Found!");
	}
	public OrderNotFoundException(String message)
	{
		super(message);
	}
}

package com.mediams.challenge.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class OrderManagementException extends Exception
{

	public OrderManagementException()
	{
		super("Sorry! Unable to process your request. Please contact our customer support.");
	}

	public OrderManagementException(String message)
	{
		super(message);
	}

	public OrderManagementException(Throwable throwable)
	{
		super(throwable);
	}
}

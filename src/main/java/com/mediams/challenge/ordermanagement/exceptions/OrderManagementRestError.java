package com.mediams.challenge.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;

public class OrderManagementRestError
{
	private HttpStatus code;

	private String message;

	public OrderManagementRestError(HttpStatus code)
	{
		this.code = code;
	}

	public void setCode(HttpStatus code)
	{
		this.code = code;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public HttpStatus getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}

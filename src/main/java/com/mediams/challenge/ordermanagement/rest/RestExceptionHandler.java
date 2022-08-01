package com.mediams.challenge.ordermanagement.rest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mediams.challenge.ordermanagement.exceptions.OrderManagementException;
import com.mediams.challenge.ordermanagement.exceptions.OrderManagementRestError;
import com.mediams.challenge.ordermanagement.exceptions.OrderNotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler({OrderManagementException.class, OrderNotFoundException.class})
	protected ResponseEntity<Object> doHandleOrderManagementException(OrderManagementException orderManagementException)
	{
		OrderManagementRestError error = new OrderManagementRestError(HttpStatus.BAD_REQUEST);
		error.setMessage(orderManagementException.getMessage());
		return ResponseEntity.badRequest().body(error);
	}
}

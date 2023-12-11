package com.mediams.challenge.ordermanagement.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mediams.challenge.ordermanagement.datatransferobjects.OrderDTO;
import com.mediams.challenge.ordermanagement.domainobjects.Order;
import com.mediams.challenge.ordermanagement.endpoints.OrderManagementEndPoints;
import com.mediams.challenge.ordermanagement.rest.RestResponse;
import com.mediams.challenge.ordermanagement.services.OrderManagementService;
import com.mediams.challenge.ordermanagement.transformers.OrderTransformer;
import com.mediams.challenge.ordermanagement.utils.RestUtil;

@RestController
@RequestMapping(OrderManagementEndPoints.ORDERS)
public class OrderController
{
	@Autowired
	private final OrderManagementService orderManagementService;

	@Autowired
	private OrderController(OrderManagementService orderManagementService)
	{
		this.orderManagementService = orderManagementService;
	}


	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> createOrder(@Validated @RequestBody OrderDTO order) throws Exception
	{
		Order orderModel = OrderTransformer.transformOrderDTO(order);
		OrderDTO orderDTO = this.orderManagementService.createOrder(orderModel);
		return ResponseEntity.ok(RestUtil.buildSuccessResponse(orderDTO));
	}

	@GetMapping()
	public ResponseEntity<RestResponse> getOrders() throws Exception
	{
		List<OrderDTO> orders = OrderTransformer.transformOrders(this.orderManagementService.getOrders());
		return ResponseEntity.ok().body(RestUtil.buildSuccessResponse(orders));
	}

	@GetMapping(value = OrderManagementEndPoints.GET_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestResponse> getOrders(@PathVariable Long orderID) throws Exception
	{
		OrderDTO orderDTO = OrderTransformer.transformOrder(orderManagementService.getOrder(orderID));
		return ResponseEntity.ok().body(RestUtil.buildSuccessResponse(orderDTO));
	}

	@PutMapping(value = OrderManagementEndPoints.UPFATE_STATUS)
	public ResponseEntity<RestResponse> updateStatus(@PathVariable Long orderID, @PathVariable String status, @Nullable @RequestParam Map<String, Object> request) throws Exception
	{
		System.out.println("Status Name: " + request.toString());
		OrderDTO updatedOrder = OrderTransformer.transformOrder(this.orderManagementService.updateStatus(orderID, status, request));
		RestResponse response = RestUtil.buildSuccessResponse("Status Successfully Updated", updatedOrder);
		return ResponseEntity.ok(response);
	}

}

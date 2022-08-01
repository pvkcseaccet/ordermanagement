package com.mediams.challenge.ordermanagement;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.mediams.challenge.ordermanagement.domainvalues.DeliveryStatus;
import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdermanagementApplicationTests
{

	@Autowired private MockMvc mockMvc;

	@Test @Order(2)
	public void contextLoads(){}

	@BeforeAll
	public void createOrders() throws Exception
	{


		this.mockMvc.perform(post("/api/v1/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestDataProvider.ORDER_CREATION_PAYLOAD1.getBytes(StandardCharsets.UTF_8)))
			.andExpect(status().isOk());

		this.mockMvc.perform(post("/api/v1/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestDataProvider.ORDER_CREATION_PAYLOAD1.getBytes(StandardCharsets.UTF_8)));

		this.mockMvc.perform(post("/api/v1/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestDataProvider.ORDER_CREATION_PAYLOAD1.getBytes(StandardCharsets.UTF_8)));
	}

	@Test @Order(2)
	public void whenOrderIDisProvided_retrievedOrderIsCorrect() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/orders/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.id")
				.value(1));
	}

	@Test @Order(3)
	public void whereNoOrderIDProvided_retrivesAllOrdersCreated() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/orders"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].id")
				.value(1));
	}

	@Test @Order(4)
	public void whenInvalidOrderIDProvided_OrderNotFoundExceptionisThrown() throws Exception
	{
		this.mockMvc.perform(get("/api/v1/orders/-1"))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.message")
				.value("Order Not Found!"));
	}

	@Test @Order(5)
	public void whenCorrectPayloadGiven_orderSuccessfullyCreated() throws Exception
	{
		this.mockMvc.perform(post("/api/v1/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestDataProvider.ORDER_CREATION_PAYLOAD1.getBytes(StandardCharsets.UTF_8)))
			.andExpect(status().isOk());
	}

	@Test @Order(6)
	public void whenNoLineItemsGiven_BadRequestisThrown() throws Exception
	{
		this.mockMvc.perform(post("/api/v1/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestDataProvider.ORDER_CREATION_PAYLOAD_WITH_EMPTY_ITEMS.getBytes(StandardCharsets.UTF_8)))
			.andExpect(status().isBadRequest());
	}

	@Test @Order(7)
	public void whenPaymentStatusUpdatedForNonExistentOrder_BadRequestisThrown() throws Exception
	{
		this.mockMvc.perform(put("/api/v1/orders/-1/status/payment")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "SUCCESS"))
			.andExpect(status().isBadRequest());
	}

	@Test @Order(8)
	public void whenPaymentStatusUpdatedForExistingOrder_StatusisUpdated() throws Exception
	{
		this.mockMvc.perform(put("/api/v1/orders/1/status/payment")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "SUCCESS"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.payment_status").value("SUCCESS"));
	}

	@Test @Order(9)
	public void whenPaymentStatusUpdatedAsSuccess_FulfillmentUpdatedPENDING() throws Exception
	{
		this.mockMvc.perform(put("/api/v1/orders/2/status/payment")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "SUCCESS"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.fulfillment_status").value("PENDING"));
	}

	@Test @Order(10)
	public void whenFulfillmentInitiatedwhilePaymentPending_BadRequestisThrown() throws Exception
	{
		this.mockMvc.perform(put("/api/v1/orders/3/status/fulfillment")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "INITIATED"))
			.andExpect(status().isBadRequest());
	}

	@Test @Order(11)
	public void whenFulfillmentStatusUpdatedAsSuccess_DeliveryUpdatedInitiated() throws Exception
	{
		Function<String, String> deliveryStatus = (status) -> status.equals(FulfillmentStatus.SUCCESS.toString())
			? DeliveryStatus.INITIATED.toString()
			: DeliveryStatus.NOT_INITIATED.toString();

		this.mockMvc.perform(put("/api/v1/orders/1/status/fulfillment")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "SUCCESS"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.delivery_status").value(deliveryStatus.apply(jsonPath("$.data.delivery_status").toString())));
	}

	@Test @Order(12)
	public void whenDeliveryStatusUpdatedAsInitiated_DeliveryUpdatedInitiated() throws Exception
	{
		Function<String, String> deliveryStatus = (status) -> status.equals(FulfillmentStatus.SUCCESS.toString())
			? DeliveryStatus.INITIATED.toString()
			: DeliveryStatus.NOT_INITIATED.toString();

		this.mockMvc.perform(put("/api/v1/orders/1/status/delivery")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.param("status", "INITIATED"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.data.delivery_status").value("INITIATED"));
	}
}

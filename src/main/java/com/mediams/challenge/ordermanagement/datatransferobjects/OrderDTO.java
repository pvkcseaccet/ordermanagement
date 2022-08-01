package com.mediams.challenge.ordermanagement.datatransferobjects;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mediams.challenge.ordermanagement.domainobjects.LineItems;
import com.mediams.challenge.ordermanagement.domainvalues.DeliveryStatus;
import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;
import com.mediams.challenge.ordermanagement.domainvalues.OrderStatus;
import com.mediams.challenge.ordermanagement.domainvalues.PaymentStatus;
import com.mediams.challenge.ordermanagement.transformers.LineItemTransformer;
import com.mediams.challenge.ordermanagement.utils.OrderUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO
{
	private Long id;

	private Double total;

	@JsonProperty("order_status") private OrderStatus orderStatus = OrderStatus.CREATED;

	@JsonProperty("payment_status") private PaymentStatus paymentStatus = PaymentStatus.PENDING;

	@JsonProperty("delivery_status") private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

	@JsonProperty("fulfillment_status") private FulfillmentStatus fulfillmentStatus = FulfillmentStatus.PENDING;

	@JsonProperty("line_items") @NotEmpty(message = "Items could not be empty.") private List<OrderLineItemDTO> lineItems = new ArrayList<>();

	@JsonProperty("created_time") public ZonedDateTime createdTime = ZonedDateTime.now(ZoneId.systemDefault());

	@JsonProperty("updated_time") public ZonedDateTime updatedTime = ZonedDateTime.now(ZoneId.systemDefault()); //Will be modified

	private OrderDTO()
	{
	}

	public OrderDTO(List<OrderLineItemDTO> lineItems)
	{
		this.lineItems = lineItems;
		this.total = OrderUtil.computeOrderValue(this);
	}

	public OrderDTO(Double total, List<OrderLineItemDTO> lineItems)
	{
		this.total = total;
		this.lineItems = lineItems;
	}

	public OrderDTO(Long id, Double total, OrderStatus orderStatus, PaymentStatus paymentStatus, DeliveryStatus deliveryStatus, FulfillmentStatus fulfillmentStatus, List<LineItems> lineItems)
	{
		this.id = id;
		this.total = total;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.deliveryStatus = deliveryStatus;
		this.fulfillmentStatus = fulfillmentStatus;
		this.lineItems = LineItemTransformer.transformOrderLineItemList(lineItems);
	}

	private void setTotal(Double total)
	{
		this.total = total;
	}

	private void setLineItems(List<OrderLineItemDTO> lineItems)
	{
		this.lineItems = lineItems;
	}

	public Long getId()
	{
		return id;
	}

	public Double getTotal()
	{
		return total;
	}

	@JsonProperty public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}

	@JsonProperty public PaymentStatus getPaymentStatus()
	{
		return paymentStatus;
	}

	@JsonProperty public DeliveryStatus getDeliveryStatus()
	{
		return deliveryStatus;
	}

	public FulfillmentStatus getFulfillmentStatus()
	{
		return fulfillmentStatus;
	}

	@JsonProperty

	public List<OrderLineItemDTO> getLineItems()
	{
		return lineItems;
	}

	@JsonProperty public ZonedDateTime getCreatedTime()
	{
		return createdTime;
	}

	@JsonProperty public ZonedDateTime getUpdatedTime()
	{
		return updatedTime;
	}
}

package com.mediams.challenge.ordermanagement.domainobjects;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.mediams.challenge.ordermanagement.domainvalues.DeliveryStatus;
import com.mediams.challenge.ordermanagement.domainvalues.FulfillmentStatus;
import com.mediams.challenge.ordermanagement.domainvalues.OrderStatus;
import com.mediams.challenge.ordermanagement.domainvalues.PaymentStatus;
import com.mediams.challenge.ordermanagement.utils.OrderUtil;

@Entity
@Table (name = "orders")
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, precision = 2, columnDefinition = "DECIMAL(7,2)")
	private Double total;

	@Enumerated(EnumType.STRING)
	@Column
	private OrderStatus orderStatus = OrderStatus.CREATED;

	@Enumerated(EnumType.STRING)
	@Column
	private PaymentStatus paymentStatus = PaymentStatus.PENDING;

	@Enumerated(EnumType.STRING)
	@Column
	private DeliveryStatus deliveryStatus = DeliveryStatus.PENDING;

	@Enumerated(EnumType.STRING)
	@Column
	private FulfillmentStatus fulfillmentStatus = FulfillmentStatus.PENDING;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<LineItems> items = new ArrayList<>();

	@Column(updatable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private final ZonedDateTime createdTime = ZonedDateTime.now(ZoneId.systemDefault());

	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime updatedTime = ZonedDateTime.now(ZoneId.systemDefault()); //Will be modified

	public Order(Long id, Double total, OrderStatus orderStatus, PaymentStatus paymentStatus, DeliveryStatus deliveryStatus, FulfillmentStatus fulfillmentStatus, List<LineItems> lineItems)
	{
		this.id = id;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.deliveryStatus = deliveryStatus;
		this.fulfillmentStatus = fulfillmentStatus;
		this.items = lineItems;
		this.total = OrderUtil.computeOrderValue(this);
	}

	public Order() {}

	public Long getID()
	{
		return id;
	}

	public Double getTotal()
	{
		return total;
	}

	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}

	public PaymentStatus getPaymentStatus()
	{
		return paymentStatus;
	}

	public DeliveryStatus getDeliveryStatus()
	{
		return deliveryStatus;
	}

	public FulfillmentStatus getFulfillmentStatus()
	{
		return fulfillmentStatus;
	}

	public List<LineItems> getItems()
	{
		return items;
	}

	public ZonedDateTime getCreatedTime()
	{
		return createdTime;
	}

	public ZonedDateTime getUpdatedTime()
	{
		return updatedTime;
	}

	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus)
	{
		this.paymentStatus = paymentStatus;
	}

	public void setDeliveryStatus(DeliveryStatus deliveryStatus)
	{
		this.deliveryStatus = deliveryStatus;
	}

	public void setFulfillmentStatus(FulfillmentStatus fulfillmentStatus)
	{
		this.fulfillmentStatus = fulfillmentStatus;
	}
}

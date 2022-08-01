package com.mediams.challenge.ordermanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mediams.challenge.ordermanagement.domainobjects.OrderTracker;

public interface OrderTrackerRepository extends CrudRepository<OrderTracker, Long>
{
}

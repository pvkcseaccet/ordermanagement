package com.mediams.challenge.ordermanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mediams.challenge.ordermanagement.domainobjects.Order;

public interface OrderRepository extends CrudRepository<Order, Long>
{

}

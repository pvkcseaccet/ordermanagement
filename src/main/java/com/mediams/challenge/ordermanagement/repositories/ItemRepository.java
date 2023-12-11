package com.mediams.challenge.ordermanagement.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mediams.challenge.ordermanagement.domainobjects.Item;

public interface ItemRepository extends CrudRepository<Item, Long>
{
}

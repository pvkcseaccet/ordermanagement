package com.mediams.challenge.ordermanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediams.challenge.ordermanagement.domainobjects.Item;
import com.mediams.challenge.ordermanagement.repositories.ItemRepository;

@Service
public class ItemService
{
	@Autowired private ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository)
	{
		this.itemRepository = itemRepository;
	}

	public Item findByID(Long id)
	{
		return this.itemRepository.findById(id).get();
	}

	public List<Item> findAll()
	{
		List<Item> items = new ArrayList<>();
		this.itemRepository.findAll().forEach(items::add);
		return items;
	}
}

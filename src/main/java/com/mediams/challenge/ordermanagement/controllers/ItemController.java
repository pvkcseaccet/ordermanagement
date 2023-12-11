package com.mediams.challenge.ordermanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediams.challenge.ordermanagement.domainobjects.Item;
import com.mediams.challenge.ordermanagement.services.ItemService;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController
{
	@Autowired private ItemService itemService;

	public ItemController(ItemService itemService)
	{
		this.itemService = itemService;
	}

	@GetMapping(path = "/{id}")
	public Item getItem(@PathVariable Long id)
	{
		return this.itemService.findByID(id);
	}

	@GetMapping(path = "")
	public List<Item> getAllItems()
	{
		return this.itemService.findAll();
	}
}

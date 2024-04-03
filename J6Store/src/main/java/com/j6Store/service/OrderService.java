package com.j6Store.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.j6Store.entity.Order;

public interface OrderService {

	Order create(JsonNode orderData);

	Order findById(Long id);

	List<Order> findByUsername(String username);
	

	

}

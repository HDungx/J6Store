package com.j6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6Store.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

}

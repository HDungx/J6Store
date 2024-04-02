package com.j6Store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6Store.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{

}

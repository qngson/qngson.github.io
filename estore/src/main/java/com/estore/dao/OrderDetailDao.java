package com.estore.dao;

import java.util.List;

import com.estore.entity.Order;
import com.estore.entity.OrderDetail;

public interface OrderDetailDao {

	OrderDetail findById(Integer id);
	List<OrderDetail> findAll();
	OrderDetail Create(OrderDetail entity);
	void update(OrderDetail entity);
	OrderDetail delete(Integer id);
	List<OrderDetail> findByOrder(Order order);
}

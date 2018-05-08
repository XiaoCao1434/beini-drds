package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.beini.drds.mapper.OrderMapper;
import com.beini.drds.service.OrderService;
import com.beini.order.entity.Order;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

//@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;

	@Override
	public Paging<Order> findAll(Pagingable pageable) {
		return mapper.findAll(pageable);
	}

	@Override
	public Order findById(String id) {
		return mapper.findOne(id);
	}

	@Override
	public Order save(Order bean) {
		return mapper.save(bean);
	}

	@Override
	public Order update(Order bean) {
		return mapper.save(bean);
	}

	@Override
	public void delete(String... id) {
		try {
			for (String str : id) {
				mapper.delete(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

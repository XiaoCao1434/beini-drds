package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.beini.drds.mapper.OrderDetailMapper;
import com.beini.drds.service.OrderDetailService;
import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Page;
import com.beini.paging.domain.Pageable;

//@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper mapper = null;

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return mapper.findAll(pageable);
	}

	@Override
	public Page<OrderDetail> findAll(String orderId, Pageable pageable) {
		return mapper.findAllByOrderUuid(orderId, pageable);
	}

	@Override
	public OrderDetail findById(String id) {
		return mapper.findOne(id);
	}

	@Override
	public OrderDetail save(OrderDetail bean) {
		return mapper.save(bean);
	}

	@Override
	public OrderDetail update(OrderDetail bean) {
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

package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.beini.drds.mapper.OrderDetailMapper;
import com.beini.drds.service.OrderDetailService;
import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

//@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailMapper mapper = null;

	@Override
	public Paging<OrderDetail> findAll(Pagingable pageable) {
		return mapper.findAll(pageable);
	}

	@Override
	public Paging<OrderDetail> findAll(String orderId, Pagingable pageable) {
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

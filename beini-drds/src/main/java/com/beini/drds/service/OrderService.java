package com.beini.drds.service;

import com.beini.order.entity.Order;
import com.beini.paging.domain.Page;
import com.beini.paging.domain.Pageable;

public interface OrderService {
	/*查询*/
	Page<Order> findAll(Pageable pageable);
	Order findById(String id);
	/*更新*/
	Order save(Order bean);
	Order update(Order bean);
	void delete(String... id);
}

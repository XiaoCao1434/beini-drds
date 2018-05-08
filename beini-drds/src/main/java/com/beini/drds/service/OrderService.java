package com.beini.drds.service;

import com.beini.order.entity.Order;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

public interface OrderService {
	/*查询*/
	Paging<Order> findAll(Pagingable pageable);
	Order findById(String id);
	/*更新*/
	Order save(Order bean);
	Order update(Order bean);
	void delete(String... id);
}

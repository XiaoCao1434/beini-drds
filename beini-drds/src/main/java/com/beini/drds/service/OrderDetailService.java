package com.beini.drds.service;

import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

public interface OrderDetailService {
	/*查询*/
	Paging<OrderDetail> findAll(Pagingable pageable);
	OrderDetail findById(String id);
	/*更新*/
	OrderDetail save(OrderDetail bean);
	OrderDetail update(OrderDetail bean);
	void delete(String... id);
	
	/*根据订单ID查询订单详情信息*/
	Paging<OrderDetail> findAll(String orderId, Pagingable pageable);
}

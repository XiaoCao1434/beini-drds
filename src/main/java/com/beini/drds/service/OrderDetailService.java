package com.beini.drds.service;

import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Page;
import com.beini.paging.domain.Pageable;

public interface OrderDetailService {
	/*查询*/
	Page<OrderDetail> findAll(Pageable pageable);
	OrderDetail findById(String id);
	/*更新*/
	OrderDetail save(OrderDetail bean);
	OrderDetail update(OrderDetail bean);
	void delete(String... id);
	
	/*根据订单ID查询订单详情信息*/
	Page<OrderDetail> findAll(String orderId, Pageable pageable);
}

package com.beini.drds.mapper;

import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Page;
import com.beini.paging.domain.Pageable;

public interface OrderDetailMapper {

	void delete(String str);

	OrderDetail save(OrderDetail bean);

	Page<OrderDetail> findAllByOrderUuid(String orderId, Pageable pageable);

	Page<OrderDetail> findAll(Pageable pageable);

	OrderDetail findOne(String id);

}

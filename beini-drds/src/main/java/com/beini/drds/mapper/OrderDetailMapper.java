package com.beini.drds.mapper;

import com.beini.order.entity.OrderDetail;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

public interface OrderDetailMapper {

	void delete(String str);

	OrderDetail save(OrderDetail bean);

	Paging<OrderDetail> findAllByOrderUuid(String orderId, Pagingable pageable);

	Paging<OrderDetail> findAll(Pagingable pageable);

	OrderDetail findOne(String id);

}

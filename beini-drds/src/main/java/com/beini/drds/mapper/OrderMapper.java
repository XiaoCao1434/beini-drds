package com.beini.drds.mapper;

import com.beini.order.entity.Order;
import com.beini.paging.domain.Paging;
import com.beini.paging.domain.Pagingable;

public interface OrderMapper {

	Paging<Order> findAll(Pagingable pageable);

	Order findOne(String id);

	Order save(Order bean);

	void delete(String str);

}

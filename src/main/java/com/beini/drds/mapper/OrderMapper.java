package com.beini.drds.mapper;

import com.beini.order.entity.Order;
import com.beini.paging.domain.Page;
import com.beini.paging.domain.Pageable;

public interface OrderMapper {

	Page<Order> findAll(Pageable pageable);

	Order findOne(String id);

	Order save(Order bean);

	void delete(String str);

}

package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beini.cache.utils.BeiniSetRedisUtil;
import com.beini.drds.entity.SimpleEmployee;
import com.beini.drds.repository.SimpleEmployeeRepository;
import com.beini.drds.service.SimpleEmployeeService;
import com.google.gson.Gson;
@Service
public class SimpleEmployeeServiceImpl implements SimpleEmployeeService {
	@Autowired
	private SimpleEmployeeRepository repository;
	@Autowired
	BeiniSetRedisUtil setRedisUtil;

	@Override
	public SimpleEmployee save(SimpleEmployee employee) {
		return repository.save(employee);
	}

	@Override
	public long saveToSaveQueue(SimpleEmployee employee) {
		String beans = new Gson().toJson(employee);
		long setStatus = setRedisUtil.sSet("saveSampleEmployee", beans);
		if(setStatus<1) {
			System.out.println("保存到saveEmployee队列失败");
			return saveToDelayQueue(beans);
		}
		return setStatus;
	}

	@Override
	public long saveToDelayQueue(String beans) {
		long setStatus = setRedisUtil.sSet("delaySampleEmployee", beans);
		if(setStatus<1) {
			System.out.println("保存到delayEmployee队列失败");
			return 0;
		}
		return setStatus;
	}

	@Override
	public SimpleEmployee getFromSaveQueue() {
		String result = ""+setRedisUtil.sPop("saveSampleEmployee");
		if(result== null || "".equals(result)) {
			return null;
		}else {
			return new Gson().fromJson(result, SimpleEmployee.class);
		}
	}
	@Override
	public SimpleEmployee getFromDelayQueue() {
		String result = ""+setRedisUtil.sPop("delaySampleEmployee");
		if(result== null || "".equals(result)) {
			return null;
		}else {
			return new Gson().fromJson(result, SimpleEmployee.class);
		}
	}

}

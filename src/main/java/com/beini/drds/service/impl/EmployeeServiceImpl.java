package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beini.cache.utils.BeiniSetRedisUtil;
import com.beini.drds.entity.Employee;
import com.beini.drds.repository.EmployeeRepository;
import com.beini.drds.service.EmployeeService;
import com.google.gson.Gson;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	BeiniSetRedisUtil setRedisUtil;

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public long saveToSaveQueue(Employee employee) {
		String beans = new Gson().toJson(employee);
		long setStatus = setRedisUtil.sSet("saveEmployee", beans);
		if(setStatus<1) {
			System.out.println("保存到saveEmployee队列失败");
			return saveToDelayQueue(beans);
		}
		return setStatus;
	}

	@Override
	public long saveToDelayQueue(String beans) {
		long setStatus = setRedisUtil.sSet("delayEmployee", beans);
		if(setStatus<1) {
			System.out.println("保存到delayEmployee队列失败");
			return 0;
		}
		return setStatus;
	}

	@Override
	public Employee getFromSaveQueue() {
		String result = ""+setRedisUtil.sPop("saveEmployee");
		if(result== null || "".equals(result)) {
			return null;
		}else {
			return new Gson().fromJson(result, Employee.class);
		}
	}
	@Override
	public Employee getFromDelayQueue() {
		String result = ""+setRedisUtil.sPop("delayEmployee");
		if(result== null || "".equals(result)) {
			return null;
		}else {
			return new Gson().fromJson(result, Employee.class);
		}
	}

}

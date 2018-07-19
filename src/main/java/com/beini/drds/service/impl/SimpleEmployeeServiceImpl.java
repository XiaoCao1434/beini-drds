package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beini.drds.entity.SimpleEmployee;
import com.beini.drds.repository.SimpleEmployeeRepository;
import com.beini.drds.service.SimpleEmployeeService;
@Service
public class SimpleEmployeeServiceImpl implements SimpleEmployeeService {
	@Autowired
	private SimpleEmployeeRepository repository;

	@Override
	public SimpleEmployee save(SimpleEmployee employee) {
		return repository.save(employee);
	}

}

package com.beini.drds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beini.drds.entity.Employee;
import com.beini.drds.repository.EmployeeRepository;
import com.beini.drds.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

}

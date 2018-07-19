package com.beini.drds.service;

import com.beini.drds.entity.Employee;

public interface EmployeeService {
	
	Employee save(Employee employee);
	
	long saveToSaveQueue(Employee employee);
	
	long saveToDelayQueue(String beans);
	
	Employee getFromSaveQueue();
	Employee getFromDelayQueue();
	
}

package com.beini.drds.service;

import com.beini.drds.entity.SimpleEmployee;

public interface SimpleEmployeeService {
	SimpleEmployee save(SimpleEmployee employee);
	
	long saveToSaveQueue(SimpleEmployee employee);
	
	long saveToDelayQueue(String beans);
	
	SimpleEmployee getFromSaveQueue();
	SimpleEmployee getFromDelayQueue();
}

package com.beini.drds.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beini.drds.entity.SimpleEmployee;
import com.beini.drds.service.SimpleEmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 简单员工控制器
 * 
 * @author lb_chen
 *
 */
@Api(value = "简单员工控制器")
@RestController
@RequestMapping("/employee1")
public class SimpleEmployeeController {

	@Autowired
	private SimpleEmployeeService employeeService;

	@ApiOperation(value = "保存员工信息到数据库")
	@GetMapping("/save")
	public String saveEmployee() {
		SimpleEmployee employee = employeeService.save(getRondomEmployee());
		return employee.getId() + employee.getName1();
	}

	@ApiOperation(value = "保存员工信息到数据库")
	@GetMapping("/saveInfo")
	public String saveEmployeeInfo(@ApiParam("员工信息") SimpleEmployee simpleEmployee) {
		SimpleEmployee employee = employeeService.save(simpleEmployee);
		return employee.getId() + employee.getName1();
	}

	@ApiOperation(value = "保存指定员工数量信息到数据库")
	@GetMapping("/save/{num}")
	public String saveEmployeeInfoNum(@PathVariable("num") int num) {
		SimpleEmployee employee = null;
		for (int i = 0; i < num; i++) {
			employee = employeeService.save(getRondomEmployee());
		}
		return employee.getId() + employee.getName1();
	}

	private SimpleEmployee getRondomEmployee() {
		SimpleEmployee employee = new SimpleEmployee();
		employee.setName1(UUID.randomUUID().toString());
		employee.setName2(UUID.randomUUID().toString());
		employee.setName3(UUID.randomUUID().toString());
		employee.setName4(UUID.randomUUID().toString());
		employee.setName5(UUID.randomUUID().toString());
		employee.setName6(UUID.randomUUID().toString());
		employee.setName7(UUID.randomUUID().toString());
		employee.setName8(UUID.randomUUID().toString());
		employee.setName9(UUID.randomUUID().toString());
		return employee;
	}
}

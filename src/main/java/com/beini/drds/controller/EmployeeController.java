package com.beini.drds.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beini.drds.entity.Employee;
import com.beini.drds.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
/**
 * 员工控制器
 * @author lb_chen
 *
 */
@Api(value = "员工控制器")
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@ApiOperation(value = "保存员工信息到数据库")
	@GetMapping("/save")
	public String saveEmployee() {
		Employee employee = employeeService.save(getRondomEmployee());
		return employee.getId()+employee.getName1();
	}
	@ApiOperation(value = "保存员工信息到数据库22")
	@GetMapping("/save/{num}")
	public String saveEmployeeNum(@PathVariable("num") int num) {
		Employee employee= null;
		for(int i = 0;i< num;i++) {
			employee = employeeService.save(getRondomEmployee());
		}
		return employee.getId()+employee.getName1();
	}
	@ApiOperation(value = "保存员工信息到保存队列")
	@GetMapping("/saveToQueue")
	public String saveToQueue() {
		log.error("saveToQueue");
		//return ""+employeeService.saveToSaveQueue(getRondomEmployee());
		return null;
	}
	@ApiOperation(value = "从保存队列中取出员工信息")
	@GetMapping("/getFromSaveQueue")
	public Employee getFromSaveQueue() {
		return employeeService.getFromSaveQueue();
	}
	@ApiOperation(value = "从延迟队列中取出员工信息")
	@GetMapping("/getFromDelayQueue")
	public Employee getFromDelayQueue() {
		return employeeService.getFromDelayQueue();
	}
	private Employee getRondomEmployee() {
		Employee employee = new Employee();
		employee.setName1(UUID.randomUUID().toString());
		employee.setName2(UUID.randomUUID().toString());
		employee.setName3(UUID.randomUUID().toString());
		employee.setName4(UUID.randomUUID().toString());
		employee.setName5(UUID.randomUUID().toString());
		employee.setName6(UUID.randomUUID().toString());
		employee.setName7(UUID.randomUUID().toString());
		employee.setName8(UUID.randomUUID().toString());
		employee.setName9(UUID.randomUUID().toString());
		employee.setName10(UUID.randomUUID().toString());
		employee.setName11(UUID.randomUUID().toString());
		employee.setName12(UUID.randomUUID().toString());
		employee.setName13(UUID.randomUUID().toString());
		employee.setName14(UUID.randomUUID().toString());
		employee.setName15(UUID.randomUUID().toString());
		employee.setName16(UUID.randomUUID().toString());
		employee.setName17(UUID.randomUUID().toString());
		employee.setName18(UUID.randomUUID().toString());
		employee.setName19(UUID.randomUUID().toString());
		employee.setName20(UUID.randomUUID().toString());
		employee.setName21(UUID.randomUUID().toString());
		employee.setName22(UUID.randomUUID().toString());
		employee.setName23(UUID.randomUUID().toString());
		employee.setName24(UUID.randomUUID().toString());
		employee.setName25(UUID.randomUUID().toString());
		employee.setName26(UUID.randomUUID().toString());
		employee.setName27(UUID.randomUUID().toString());
		employee.setName28(UUID.randomUUID().toString());
		employee.setName29(UUID.randomUUID().toString());
		employee.setName30(UUID.randomUUID().toString());
		employee.setName31(UUID.randomUUID().toString());
		employee.setName32(UUID.randomUUID().toString());
		employee.setName33(UUID.randomUUID().toString());
		employee.setName34(UUID.randomUUID().toString());
		employee.setName35(UUID.randomUUID().toString());
		employee.setName36(UUID.randomUUID().toString());
		employee.setName37(UUID.randomUUID().toString());
		employee.setName38(UUID.randomUUID().toString());
		employee.setName40(UUID.randomUUID().toString());
		employee.setName41(UUID.randomUUID().toString());
		employee.setName42(UUID.randomUUID().toString());
		employee.setName43(UUID.randomUUID().toString());
		employee.setName44(UUID.randomUUID().toString());
		employee.setName45(UUID.randomUUID().toString());
		employee.setName46(UUID.randomUUID().toString());
		employee.setName47(UUID.randomUUID().toString());
		employee.setName48(UUID.randomUUID().toString());
		employee.setName49(UUID.randomUUID().toString());
		employee.setName50(UUID.randomUUID().toString());
		employee.setName51(UUID.randomUUID().toString());
		employee.setName52(UUID.randomUUID().toString());
		employee.setName53(UUID.randomUUID().toString());
		employee.setName54(UUID.randomUUID().toString());
		employee.setName55(UUID.randomUUID().toString());
		employee.setName56(UUID.randomUUID().toString());
		employee.setName57(UUID.randomUUID().toString());
		employee.setName58(UUID.randomUUID().toString());
		employee.setName59(UUID.randomUUID().toString());
		employee.setName60(UUID.randomUUID().toString());
		return employee;
	}
}

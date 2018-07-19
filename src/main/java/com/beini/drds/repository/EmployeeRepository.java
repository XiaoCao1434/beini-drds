package com.beini.drds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beini.drds.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

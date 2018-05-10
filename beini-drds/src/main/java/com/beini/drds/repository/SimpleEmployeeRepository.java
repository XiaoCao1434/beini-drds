package com.beini.drds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beini.drds.entity.SimpleEmployee;

public interface SimpleEmployeeRepository extends JpaRepository<SimpleEmployee, Integer> {

}

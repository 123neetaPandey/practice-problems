package com.SixDee.springboot.CrudDemo.employee.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SixDee.springboot.CrudDemo.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
 //there  is no need of writing  any method
	
	
}

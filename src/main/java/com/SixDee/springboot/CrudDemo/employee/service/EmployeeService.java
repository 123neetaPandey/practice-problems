package com.SixDee.springboot.CrudDemo.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.SixDee.springboot.CrudDemo.employee.entity.Employee;

public interface EmployeeService {
	
   public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee  theEmployee);
	
	public void deleteById(int theId);

	public Page<Employee> findJsondataByCondition(String orderBy, String direction, int page, int size);
	
}

package com.SixDee.springboot.CrudDemo.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SixDee.springboot.CrudDemo.employee.dao.EmployeeRepository;
import com.SixDee.springboot.CrudDemo.employee.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
	private EmployeeRepository  employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository  theEmployeeRepository) {
		this.employeeRepository=theEmployeeRepository;
	}
	
	@Override
	
	public List<Employee> findAll() {
		
		return employeeRepository.findAll();
	}

	@Override
	
	public Employee findById(int theId) {
		
		Optional<Employee>  result= employeeRepository.findById(theId);
		
		Employee theEmployee=null;
		
		if(result.isPresent()) {
			theEmployee= result.get();
		}
		else {
			throw new RuntimeException("Did not find employee id-"+theId);
		}
		return theEmployee;
	}

	@Override
	
	public void save(Employee theEmployee) {
		

		employeeRepository.save(theEmployee);
	}

	@Override
	
	public void deleteById(int theId) {
		
		employeeRepository.deleteById(theId);

	}

	@Override
	public Page<Employee> findJsondataByCondition(String orderBy, String direction, int page, int size) {
		
		Sort sort=null;
		
		if(direction.equals("ASC")) {
			sort= new Sort(new Sort.Order(Direction.ASC , orderBy));
		}
		if(direction.equals("DESC")) {
			sort= new Sort(new Sort.Order(Direction.DESC , orderBy));
		}
		Pageable 	pageable = new PageRequest(page , size ,sort);
		
		Page<Employee> data= employeeRepository.findAll(pageable);
		
		return data;
	}

}













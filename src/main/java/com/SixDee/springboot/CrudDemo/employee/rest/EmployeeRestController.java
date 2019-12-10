package com.SixDee.springboot.CrudDemo.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SixDee.springboot.CrudDemo.employee.entity.Employee;
import com.SixDee.springboot.CrudDemo.employee.service.EmployeeService;

@RestController
@RequestMapping("/employeeApi")
public class EmployeeRestController {

	private EmployeeService  employeeService;
	//quick and best :inject employee service 
     
	@Autowired
	public EmployeeRestController(EmployeeService   theEmployeeService) {
		this.employeeService= theEmployeeService;
	}
	
	
	@RequestMapping(value="/employeeCondionalPagination" , params= {"orderBy" , "direction" , "page" , "size"} , method= RequestMethod.GET)
	@ResponseBody
	public Page<Employee> findJsondataByPageAndSize(@RequestParam("orderBy") String orderBy , @RequestParam("direction") String direction ,
			                                     @RequestParam("page") int page , @RequestParam("size") int size){
		
		Page<Employee> list= employeeService.findJsondataByCondition(orderBy , direction ,page, size );
		return list;
	}
	
	// expose "/employee" and return list of employees
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
		
	}
	
	//add mapping for GET /employees/{employeeId}
	
      @GetMapping("/employees/{employeeId}")
      public Employee getEmployee(@PathVariable int employeeId) {
    	  
    	  Employee theEmployee= employeeService.findById(employeeId);
    	  
    	  if(theEmployee == null) {
    		  throw new RuntimeException("Employee id is not found--"+employeeId);
    	  }
    	  
    	  return theEmployee;
      }
      
      //add mapping for POST /employee - add new employee
      
      @PostMapping("/employees")
      public Employee addEmployee(@RequestBody Employee theEmployee ) {
    	  
    	  //also just in case they pass an id in json  , set id to 0
    	  //this is to force a save of new item ..instead of update
    	  
    	  theEmployee.setId(0);
    	  employeeService.save(theEmployee);
    	  return theEmployee;
    	  
      }
      
      //add mapping for PUT /employee - update existing employee
      
      @PutMapping("/employees")
      public Employee updateEmployee(@RequestBody Employee theEmployee) {
    	  
    	  employeeService.save(theEmployee);
    	  return theEmployee;
      }
      
      //add mapping for DELETE /employee /{employeeId}-delete employee
      
      @DeleteMapping("/employees/{employeeId}")
      public String deleteEmployee(@PathVariable int employeeId) {
    	  
    	  Employee tempEmployee= employeeService.findById(employeeId);
    	  
    	  if(tempEmployee == null) {
    		  throw new RuntimeException("Employee id not found-"+employeeId);
    	  }
    	  
    	  employeeService.deleteById(employeeId);
    	  return "Deleted employee id-"+employeeId;
      }
      
      
      
}



















































package com.SixDee.springboot.CrudDemo.employee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
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

//import com.SixDee.springboot.CrudDemo.employee.entity.Employee;
//import com.SixDee.springboot.CrudDemo.employee.service.EmployeeService;
//import com.SixDee.springboot.CrudDemo.eventhistory.service.EventHistoryService;
import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;
import com.SixDee.springboot.CrudDemo.eventhistory.service.EventHistoryService;

@RestController
@RequestMapping("/eventHistoryApi")
public class EventHistoryRestController {

	//private EventHistoryService  eventHistoryService;
	//quick and best :inject employee service 
    
	@Autowired
	private EventHistoryService eventHistoryService;
	/*
	 * public EventHistoryRestController(EventHistoryService theEventHistoryService)
	 * { this.eventHistoryService= theEventHistoryService; }
	 */
	
	
	
	@RequestMapping(value="/eventHistoryCondionalPagination" , params= {"orderBy" , "direction" , "page" , "size"} , method= RequestMethod.GET)
	@ResponseBody
	public Page<EventHostory> findJsondataByPageAndSize(@RequestParam("orderBy") String orderBy , @RequestParam("direction") String direction ,
			                                     @RequestParam("page") int page , @RequestParam("size") int size){
		
		Page<EventHostory> list= eventHistoryService.findJsondataByCondition(orderBy , direction ,page, size );
		return list;
	}
	
	// expose "/employee" and return list of employees
	
	@GetMapping("/eventHistory")
	public List<EventHostory> findAll(){
		return eventHistoryService.findAll();
		
	}
	
	//add mapping for GET /employees/{employeeId}
	
      @GetMapping("/eventHistory/{serialNo}")
      public EventHostory  getEventHistory(@PathVariable int serialNo) {
    	  
    	  EventHostory theEventHostory= eventHistoryService.findById(serialNo);
    	  
    	  if(theEventHostory == null) {
    		  throw new RuntimeException("Serial_No  is not found--"+serialNo);
    	  }
    	  
    	  return theEventHostory;
      }
      
      //add mapping for POST /employee - add new employee
      
      @PostMapping("/eventHistory")
      public EventHostory addEventHostory(@RequestBody EventHostory theEventHostory ) {
    	  
    	  //also just in case they pass an id in json  , set id to 0
    	  //this is to force a save of new item ..instead of update
    	  
    	  theEventHostory.setSerial_No(0);
    	  eventHistoryService.save(theEventHostory);
    	  return theEventHostory;
    	  
      }
      
      //add mapping for PUT /employee - update existing employee
      
      @PutMapping("/eventHistory")
      public EventHostory updateEventHistory(@RequestBody EventHostory theEventHostory) {
    	  
    	  eventHistoryService.save(theEventHostory);
    	  return theEventHostory;
      }
      
      //add mapping for DELETE /employee /{employeeId}-delete employee
      
      @DeleteMapping("/eventHistory/{serialNo}")
      public String deleteEventHistory(@PathVariable int serialNo) {
    	  
    	  EventHostory tempEventHistory= eventHistoryService.findById(serialNo);
    	  
    	  if(tempEventHistory == null) {
    		  throw new RuntimeException("Serial no not found-"+serialNo);
    	  }
    	  
    	  eventHistoryService.deleteById(serialNo);
    	  return "Deleted Serial_No id-"+serialNo;
      }
}

package com.SixDee.springboot.CrudDemo.eventhistory.service;

import java.util.List;

import org.springframework.data.domain.Page;
//import com.SixDee.springboot.CrudDemo.employee.entity.Employee;
//import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;
import org.springframework.stereotype.Service;

import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;

//import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;


public  interface EventHistoryService {

public List<EventHostory> findAll();
	
	public EventHostory findById(int serialNo);
	
	public void save(EventHostory  theEventHostory);
	
	public void deleteById(int serialNo);

	public Page<EventHostory> findJsondataByCondition(String orderBy, String direction, int page, int size);
	
}

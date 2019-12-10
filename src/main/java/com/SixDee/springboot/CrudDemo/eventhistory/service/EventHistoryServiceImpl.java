package com.SixDee.springboot.CrudDemo.eventhistory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.SixDee.springboot.CrudDemo.eventhistory.dao.EventHistoryRepository;
import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;

public class EventHistoryServiceImpl implements EventHistoryService {

	private EventHistoryRepository  eventHistoryRepository;
	
	@Autowired
	public EventHistoryServiceImpl(EventHistoryRepository  theEventHistoryRepository) {
		this.eventHistoryRepository=theEventHistoryRepository;
	}
	@Override
	public List<EventHostory> findAll() {
		return eventHistoryRepository.findAll();
	}

	@Override
	public EventHostory findById(int serialNo) {
Optional<EventHostory>  result= eventHistoryRepository.findById(serialNo);
		
      EventHostory theEventHostory=null;
		
		if(result.isPresent()) {
			theEventHostory= result.get();
		}
		else {
			throw new RuntimeException("Did not find EventHostory id-"+serialNo);
		}
		return theEventHostory;
	}

	@Override
	public void save(EventHostory theEventHostory) {
		eventHistoryRepository.save(theEventHostory);
	}

	@Override
	public void deleteById(int serialNo) {
		eventHistoryRepository.deleteById(serialNo);
		
	}

	@Override
	public Page<EventHostory> findJsondataByCondition(String orderBy, String direction, int page, int size) {
      Sort sort=null;
		
		if(direction.equals("ASC")) {
			sort= new Sort(new Sort.Order(Direction.ASC , orderBy));
		}
		if(direction.equals("DESC")) {
			sort= new Sort(new Sort.Order(Direction.DESC , orderBy));
		}
		Pageable 	pageable = new PageRequest(page , size ,sort);
		
		Page<EventHostory> data= eventHistoryRepository.findAll(pageable);
		
		return data;
	}

}

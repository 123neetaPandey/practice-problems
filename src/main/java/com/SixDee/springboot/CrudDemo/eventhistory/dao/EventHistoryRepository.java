package com.SixDee.springboot.CrudDemo.eventhistory.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SixDee.springboot.CrudDemo.eventhostory.entity.EventHostory;

public interface EventHistoryRepository extends JpaRepository<EventHostory, Integer> {

}

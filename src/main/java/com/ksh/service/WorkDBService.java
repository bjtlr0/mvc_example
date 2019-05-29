package com.ksh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ksh.mapper.work.TasksMapperDao;

@Service
public class WorkDBService {
	@Autowired
	private TasksMapperDao workMapperDao;
	
	@Transactional
	public void transactionTest(boolean exceptionFlag){
		// blar;
	}
}

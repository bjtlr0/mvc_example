package com.ksh.mapper.work;

import java.util.List;

import com.ksh.vo.work.TasksVO;

public interface TasksMapper {
	public TasksVO getTasks(long id);
	public List<TasksVO> getTasksList();
}

package com.ksh.mapper.work;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.ksh.vo.work.TasksVO;

@Repository("TasksMapperDao")
public class TasksMapperDao implements TasksMapper {
	@Autowired
	@Qualifier("workSqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public TasksVO getTasks(long id) {
		return sqlSessionTemplate.selectOne("getTasks", id);
	}

	@Override
	public List<TasksVO> getTasksList() {
		List<TasksVO> list = sqlSessionTemplate.selectList("getTasksList");
		return list;
	}

}

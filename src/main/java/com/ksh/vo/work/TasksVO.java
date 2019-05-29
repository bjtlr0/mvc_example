package com.ksh.vo.work;

import java.sql.Date;
import lombok.Data;

@Data
public class TasksVO {
	private long id;
	private String task_name;
	private String task_info;
	private String auditor_id;
	private Date start_time;
	private Date end_time;
}

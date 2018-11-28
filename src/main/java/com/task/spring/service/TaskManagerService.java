package com.task.spring.service;

import java.util.List;

import com.task.spring.model.TaskManager;

public interface TaskManagerService {

	long save(TaskManager tb);
	
	TaskManager get(int id);
	
	List<TaskManager> list();
	
	void update (int id, TaskManager tasks);
	
	void delete (int id);
	
	List<TaskManager> view(String Task,String parentId,String PriorityFrom,String Priorityto,String startDate,String EndDate);
}

package com.task.spring.dao;

import java.util.List;

import com.task.spring.model.TaskManager;

public interface TaskManagerDAO {

	long save(TaskManager tb);
	
	TaskManager get(int id);
	
	List<TaskManager> list();
	
	void update (int id, TaskManager tasks);
	
	void delete (int id);

	List<TaskManager> view(String query) ;
	
	
	
}

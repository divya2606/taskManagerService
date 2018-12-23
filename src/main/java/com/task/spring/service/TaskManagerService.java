package com.task.spring.service;

import java.text.ParseException;
import java.util.List;

import com.task.spring.model.ParentTask;
import com.task.spring.model.Project;
import com.task.spring.model.Task;
import com.task.spring.model.TaskManager;
import com.task.spring.model.User;

public interface TaskManagerService {

	long save(TaskManager tb);
	
	TaskManager get(int id);
	
	List<TaskManager> list();
	
	void update (int id, TaskManager tasks);
	
	void delete (int id);
	
	List<TaskManager> view(String Task,String parentId,String PriorityFrom,String Priorityto,String startDate,String EndDate);
	
	
	public long addProject(Project project) ;
	
	public long addUser(User user) ;
	
	public int addTask(Task task);
	
	public List<User> getAllUsers();
	
	public void userDelete(int id);
	
	public void updateUser(int id, User user);
	
	public List<User> getAllUsers(int id);
	
	public List<Project> getProjects() throws ParseException;
	
	public List<Project> getProjectsOrder(int id) throws ParseException ;
	
	public void updateProject(int id, Project project);
	
	public List<Task> getTasks() throws ParseException;
	
	public List<Task> getTasksOrder(int id) throws ParseException; 
	
	public List<Project> getAllProjects() throws ParseException ;
	
	public List<ParentTask> getAllParentProjects() throws ParseException ;
	
	public void deleteTask(int id);
	
	public void updateTasks(int id, Task task);
	
	public void endTask(int id);
	
}

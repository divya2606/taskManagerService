package com.task.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.spring.dao.TaskManagerDAO;
import com.task.spring.model.TaskManager;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	@Autowired
	private TaskManagerDAO taskManagerDAO;
	
	@Override
	@Transactional
	public long save(TaskManager tb) {
		// TODO Auto-generated method stub
		return taskManagerDAO.save(tb);
	}

	@Override
	@Transactional
	public TaskManager get(int id) {
		return taskManagerDAO.get(id);
		
	}

	@Override
	@Transactional
	public List<TaskManager> list() {
		// TODO Auto-generated method stub
		return taskManagerDAO.list();
	}

	@Override
	@Transactional
	public void update(int id, TaskManager tasks) {
		taskManagerDAO.update(id, tasks);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		taskManagerDAO.delete(id);
	}

	@Override
	@Transactional
	public List<TaskManager> view(String Task,String parentId,String PriorityFrom,String Priorityto,String startDate,String endDate) {
		String query="from TaskManager where ";
		String combineQuery ="";
		if(Task!=null && Task!="")
			combineQuery = combineQuery +" Task ='" + Task +"'";
		if(parentId!=null && parentId !="") {
		if(combineQuery!="")	{
					combineQuery=combineQuery +" and parentId = '"+ parentId+"'";
		}
		else 
			combineQuery=combineQuery+" parentId ='"+ parentId+"'";
		}
		if(PriorityFrom!=null && PriorityFrom!="" && Priorityto!=null && Priorityto!="") {
			if(combineQuery!="") {	
			combineQuery=combineQuery+" and priority >= "+ PriorityFrom + " and "+ "priority <= "+ Priorityto ;
			}
			else
			combineQuery=combineQuery+" priority >= "+ PriorityFrom + " and "+ "priority <= "+Priorityto ;
		}else if(PriorityFrom!=null && PriorityFrom!="" && (Priorityto==null || Priorityto=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and priority >= "+ PriorityFrom ;
			}
			else
			{
				combineQuery=combineQuery+" priority >= "+ PriorityFrom ;
			}
		}
		else if(Priorityto!=null && Priorityto!="" && (PriorityFrom==null || PriorityFrom=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and priority <= "+ Priorityto ;
			}
			else
			{
				combineQuery=combineQuery+" priority <= "+ Priorityto ;
			}
		}
		if(startDate!=null && startDate!="" && endDate!=null && endDate!="") {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and startDate>= '"+ startDate + "' and "+ " endDate <= '"+ endDate +"'" ;
				}
				else
				combineQuery=combineQuery+" startDate >= '"+ startDate + "' and "+ " endDate <= '"+endDate  +"'" ;
		}else if(startDate!=null && startDate!="" && (endDate==null || endDate=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and startDate >= '"+ startDate  +"'" ;
			}
			else
			{
				combineQuery=combineQuery+" startDate >= '"+ startDate  +"'" ;
			}
		}else if(endDate!=null && endDate!="" && (startDate==null || startDate=="")) {
			if(combineQuery!="") {	
				combineQuery=combineQuery+" and endDate <= '"+ endDate +"'" ;
			}
			else
			{
				combineQuery=combineQuery+" endDate <= '"+ endDate +"'" ;
			}
		}

		query=query+combineQuery;
		/*if(Task!=null && Task!="" && parentId!=null && parentId !="" && PriorityFrom!=null && PriorityFrom!="" && Priorityto!=null && Priorityto!="" 
			&& startDate!=null && startDate!="" && endDate!=null && endDate!=""	) {
			query="from TaskManager ";
		}*/
		if(Task!="" && parentId !="" && PriorityFrom!="" && Priorityto!="" 
		&& startDate!="" && endDate!=""	) {
		query=query;
		}
		else {
			query="from TaskManager ";	
		}
		
		List<TaskManager> li =taskManagerDAO.view(query);
		return li;
	}
	
}

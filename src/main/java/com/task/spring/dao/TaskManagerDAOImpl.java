package com.task.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.spring.model.TaskManager;

@Repository
public class TaskManagerDAOImpl implements TaskManagerDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long save(TaskManager tb) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(tb);
		return tb.getTaskId();
	}

	@Override
	public TaskManager get(int id) {
		return sessionFactory.getCurrentSession().get(TaskManager.class, id);
		
	}

	@Override
	public List<TaskManager> list() {
		// TODO Auto-generated method stub
		List<TaskManager> l= sessionFactory.getCurrentSession().createQuery("from TaskManager").list();
		return l;
	}

	@Override
	public void update(int id, TaskManager tasks) {
		Session session =sessionFactory.getCurrentSession();
		TaskManager oldTask =session.byId(TaskManager.class).load(id);
		oldTask.setTask(tasks.getTask());
		oldTask.setParentId(tasks.getParentId());
		oldTask.setPriority(tasks.getPriority());
		oldTask.setStartDate(tasks.getStartDate());
		oldTask.setEndDate(tasks.getEndDate());
		
		session.flush();
	}

	@Override
	public void delete(int id) {
		Session session =sessionFactory.getCurrentSession();
		TaskManager oldTask =session.byId(TaskManager.class).load(id);
		session.delete(oldTask);
		
	}
	
	public List<TaskManager> view(String query) {
		System.out.println("query=== "+query);
		List<TaskManager> l= sessionFactory.getCurrentSession().createQuery(query).list();
		return l;
	}

}

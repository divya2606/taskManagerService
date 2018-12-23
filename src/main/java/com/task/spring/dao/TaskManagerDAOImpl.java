package com.task.spring.dao;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.task.spring.model.ParentTask;
import com.task.spring.model.Project;
import com.task.spring.model.Task;
import com.task.spring.model.TaskManager;
import com.task.spring.model.User;

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

	
	/* Second Phase*/
	

	@Override
	public long addProject(Project project) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(project);
		return project.getProjectId();
	}
	
	@Override
	public long addUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
		return user.getUserId();
	}
	
	@Override
	public int addParentTask(ParentTask task) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(task);
		return task.getPid();
	}
	
	
	
	@Override
	public int addTask(Task task) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(task);
		return task.getTaskId();
	}
	
	@Override
	public int addTask(int id,Task task) {
		// TODO Auto-generated method stub
		Session session =sessionFactory.getCurrentSession();
		Task oldTask =session.byId(Task.class).load(id);
		oldTask.setTedate(task.getTedate());
		oldTask.setTsdate(task.getTsdate());
	    oldTask.setTpriority(task.getTpriority());
	    oldTask.setPtask(task.getPtask());
		session.flush();
		return task.getTaskId();
	}
	
	@Override
	public void updateUser(String id, Task task) {
		Session session =sessionFactory.getCurrentSession();
		String hql = "from user where fname  = :employee_id";
		Query query = session.createQuery(hql);
		query.setParameter("employee_id", id);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			User oldUser =(User) itr.next();
			User oldUser1 =session.byId(User.class).load(oldUser.getUserId());
			String getTask=String.valueOf(task.getTaskId());
			String getProject=String.valueOf(task.getProjectId());
			oldUser1.setTid(getTask);
			oldUser1.setProjectid(getProject);
			session.flush();
		}
		
	}
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		return l;
	}
	
	@Override
	public void userDelete(int id) {
		Session session =sessionFactory.getCurrentSession();
		User oldTask =session.byId(User.class).load(id);
		session.delete(oldTask);
		
	}
	
	@Override
	public void updateUserwithId(int id, User user) {
		Session session =sessionFactory.getCurrentSession();
		User oldTask =session.byId(User.class).load(id);
		oldTask.setEid(user.getEid());
		oldTask.setFname(user.getFname());
		oldTask.setLname(user.getLname());
	
		session.flush();
	}
	
	@Override
	public List<User> getAllUsers(int id) {
		// TODO Auto-generated method stub
		List<User> l =null;
		if(id==1) {
		 l= sessionFactory.getCurrentSession().createQuery("from user order by fname asc").list();
		 return l;
		}
		else if(id==2) {
			 l= sessionFactory.getCurrentSession().createQuery("from user order by lname asc").list();
			 return l;
			}
		else if(id==3) {
			 l= sessionFactory.getCurrentSession().createQuery("from user order by eid asc").list();
			 return l;
			}
		else {
			 l= sessionFactory.getCurrentSession().createQuery("from user").list();
			 return l;
		}
	}
		
	@Override
	public List<Project> getProjects() throws ParseException {
		// TODO Auto-generated method stub
		//List<Project> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		//return l;
		List<Project> l=new ArrayList<Project>();
		Project proj =new Project();
		Session session =sessionFactory.getCurrentSession();
		String hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount, a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority";
		Query query = session.createQuery(hql);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =new Project();
			Object[] object = (Object[]) itr.next();
			String client = String.valueOf(object[0]);
			proj.setTaskCount(Integer.parseInt(String.valueOf(object[0])));
			proj.setProject(String.valueOf(object[1]));
			String s=String.valueOf(object[2]);
			String s2=String.valueOf(object[3]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s); 
			proj.setEdate(date2);
			proj.setSdate(date1);
			proj.setPriority(Integer.parseInt(String.valueOf(object[4])));
			proj.setTcomCount(Integer.parseInt(String.valueOf(object[5])));
			proj.setProjectId(Integer.parseInt(String.valueOf(object[6])));
			l.add(proj);
		}
		return l;
	}
	
	@Override
	public List<Project> getProjectsOrder(int id) throws ParseException {
		// TODO Auto-generated method stub
		//List<Project> l= sessionFactory.getCurrentSession().createQuery("from user").list();
		//return l;
		List<Project> l=new ArrayList<Project>();
		Project proj =new Project();
		Session session =sessionFactory.getCurrentSession();
		String hql="";
		if(id==1) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.sdate asc";
		}
		else if(id==2) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.edate asc";
		}
		else if(id==3) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by a.priority asc";
		}
		else if(id==4) {
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority order by tcomCount asc";
		}
		else{
			 hql = "select count(b.pname) as taskCount,a.project,a.sdate,a.edate,a.priority,COUNT(CASE WHEN b.stat = 'completed' THEN 0 END) as tcomCount,a.projectId  from project a , task b  where a.project=b.pname  group by a.project,a.sdate,a.edate,a.priority";
		}
		Query query = session.createQuery(hql);
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =new Project();
			Object[] object = (Object[]) itr.next();
			String client = String.valueOf(object[0]);
			proj.setTaskCount(Integer.parseInt(String.valueOf(object[0])));
			proj.setProject(String.valueOf(object[1]));
			String s=String.valueOf(object[2]);
			String s2=String.valueOf(object[3]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s2); 
			proj.setEdate(date2);
			proj.setSdate(date1);
			proj.setPriority(Integer.parseInt(String.valueOf(object[4])));
			proj.setTcomCount(Integer.parseInt(String.valueOf(object[5])));
			proj.setProjectId(Integer.parseInt(String.valueOf(object[6])));
			l.add(proj);
		}
		return l;
	}
	
	@Override
	public void updateProjectwithId(int id, Project project) {
		Session session =sessionFactory.getCurrentSession();
		Project oldTask =session.byId(Project.class).load(id);
		oldTask.setProject(project.getProject());
		oldTask.setSdate(project.getSdate());
		oldTask.setEdate(project.getEdate());
		oldTask.setPriority(project.getPriority());
	
		session.flush();
	}
	
	
	
	@Override
	public void endTask(int id) {
		Session session =sessionFactory.getCurrentSession();
		Task oldtask =session.byId(Task.class).load(id);
		oldtask.setStat("completed");
		session.flush();
	}
	
	@Override
	public List<Task> getTasks() throws ParseException {
		// TODO Auto-generated method stub
//		List<Task> l= sessionFactory.getCurrentSession().createQuery("from task").list();
		List<Task> l=new ArrayList<Task>();
		Task task =new Task();
		Session session =sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b.taskId,b.ptask,b.pname,b.tname,b.tsdate,b.tedate,b.tpriority,a.eid  from user a, task b where a.projectid=b.projectId\r\n" + 
				"");
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			task =new Task();
			Object[] object = (Object[]) itr.next();
			
			String s=String.valueOf(object[4]);
			String s2=String.valueOf(object[5]);
			Date date1=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s);  
			Date date2=(Date) new SimpleDateFormat("yyyy-MM-dd").parse(s2); 
			task.setEmpId(String.valueOf(object[7]));
			task.setPname(String.valueOf(object[2]));
			task.setPtask(String.valueOf(object[1]));
			task.setTaskId(Integer.parseInt(String.valueOf(object[0])));
			task.setTedate(date2);
			task.setTname(String.valueOf(object[3]));
			task.setTsdate(date1);
			task.setTpriority(String.valueOf(object[6]));
			
			l.add(task);
	
			 }
		return l;
	}
	
	@Override
	public List<Task> getTasksOrder(int id) {
		// TODO Auto-generated method stub
		List<Task> l =null;
		if(id==1) {
		 l= sessionFactory.getCurrentSession().createQuery("from task order by tsdate asc").list();
		 return l;
		}
		else if(id==2) {
			 l= sessionFactory.getCurrentSession().createQuery("from task order by tedate asc").list();
			 return l;
			}
		else if(id==3) {
			 l= sessionFactory.getCurrentSession().createQuery("from task  order by tpriority asc").list();
			 return l;
			}
		else if(id==4) {
			 l= sessionFactory.getCurrentSession().createQuery("from task where stat='completed' order by taskId asc").list();
			 return l;
			}
		else {
			 l= sessionFactory.getCurrentSession().createQuery("from task").list();
			 return l;
		}
	}
	
	
	
	@Override
	public List<Project> getAllProjects() {
		// TODO Auto-generated method stub
		List<Project> l= sessionFactory.getCurrentSession().createQuery("from project").list();
		return l;
	}
	
	@Override
	public List<ParentTask> getAllParentProjects() {
		// TODO Auto-generated method stub
		List<ParentTask> l= sessionFactory.getCurrentSession().createQuery("from parent").list();
		return l;
	}
	

	@Override
	public void deleteTask(int id) {
		Session session =sessionFactory.getCurrentSession();
		Task oldTask =session.byId(Task.class).load(id);
		session.delete(oldTask);
		
	}

	

	@Override
	public int getProjectIds(Task task) {
		Session session =sessionFactory.getCurrentSession();
		String hql = "from project where project  = :project";
		Query query = session.createQuery(hql);
		Project proj =null;
		query.setParameter("project", task.getPname());
		List results = query.list();
		Iterator itr =results.iterator();
		while(itr.hasNext()) {
			proj =(Project) itr.next();
					}
		
		return proj.getProjectId();
		
	}

}

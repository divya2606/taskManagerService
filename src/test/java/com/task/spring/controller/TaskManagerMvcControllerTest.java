package com.task.spring.controller;


import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.task.spring.config.AppConfig;
import com.task.spring.model.Project;
import com.task.spring.model.Task;
import com.task.spring.model.TaskManager;
import com.task.spring.model.User;
import com.task.spring.service.TaskManagerService;




class TaskManagerController1{
	
}

@Configuration
@ComponentScan(basePackages = {"com.task.spring"})

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class TaskManagerMvcControllerTest {

	@Autowired
	private TaskManagerService  taskManagerServiceTest;
	
	
	@BeforeClass
	public static void setUp() {
		System.out.println("-----> SETUP <-----");
	}
	
	@Test
	public void testListSize() {
		List<TaskManager> list=taskManagerServiceTest.list();
		System.out.println("list size =" + list.size());
		assertEquals(2, list.size());
	}
	
	@Test
	public void testData() {
		TaskManager tb =  taskManagerServiceTest.get(43);
		assertEquals("Task1", tb.getTask());
	}
	
	@Test
	public void testTaskData() {
		TaskManager tb =  taskManagerServiceTest.get(3);
		assertNull(tb);
	}
	
	@Test
	public void testData1() {
		TaskManager tb =  taskManagerServiceTest.get(44);
		assertEquals("Parent Task4", tb.getParentId());
	}
	
	@Test
	public void testgetusers() {
		List<User> list =  taskManagerServiceTest.getAllUsers();
		assertNotNull(list);
	}
	
	@Test
	public void testuserid() {
		List<User> list=taskManagerServiceTest.getAllUsers(4);
		assertNotNull(list);
	}
	
	@Test
	public void testprojects() throws ParseException {
		List<Project> list =  taskManagerServiceTest.getProjects();
		assertNotNull(list);
	}

	
	@Test
	public void testprojectsId() throws ParseException {
		List<Project> list =  taskManagerServiceTest.getProjectsOrder(1);
		assertNotNull(list);
	}
	
	@Test
	public void testtasks() throws ParseException {
		List<Task> list =  taskManagerServiceTest.getTasks();
		assertNotNull(list);
	}
	
	@Test
	public void testtasksId() throws ParseException {
		List<Task> list =  taskManagerServiceTest.getTasksOrder(1);
		assertNotNull(list);
	}
	
	
}

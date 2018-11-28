package com.task.spring.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.task.spring.exception.TaskException;
import com.task.spring.model.ErrorResponse;
import com.task.spring.model.TaskManager;
import com.task.spring.service.TaskManagerService;

@CrossOrigin("*")
@RestController
public class TaskManagerController  {

	@Autowired
	private TaskManagerService  taskManagerService;
	
	@GetMapping("/api/taskmanager")
	public ResponseEntity<List<TaskManager>> list() throws ParseException, TaskException{
		List<TaskManager> list =  taskManagerService.list();
			//return ResponseEntity.ok().body(list);
//		if(list.size()<1)
//				throw new TaskException("No Tasks are present now");
				return ResponseEntity.ok().body(list);
			
	}
	
	@GetMapping("/api/taskmanager/{id}")
	public ResponseEntity<TaskManager> get(@PathVariable("id") int id) throws TaskException{
		System.out.println("id="  + id);
		TaskManager tb =  taskManagerService.get(id);
		if(tb!= null)
		return ResponseEntity.ok().body(tb);
		else
			throw new TaskException("Given Task is not Present ");
	}
	
	
	@PostMapping("/api/task")
	
	public ResponseEntity<?> save(@RequestBody TaskManager tb){
		int id = (int) taskManagerService.save(tb);
		return ResponseEntity.ok().body("Task Id" + id);
	}
	
@PutMapping("/api/task/{id}")
	
	public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody TaskManager tb){
		  taskManagerService.update(id, tb);
		return ResponseEntity.ok().body("Updated Task Id") ;
	}
	

@DeleteMapping("/api/task/{id}")
	
	public ResponseEntity<?> delete(@PathVariable("id") int id){
	System.out.println("ID===" + id);
		  taskManagerService.delete(id);
		return ResponseEntity.ok().body("Deleted Task Id") ;
	}
	

@ExceptionHandler(TaskException.class)
public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	ErrorResponse error = new ErrorResponse();
	error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
	error.setMessage(ex.getMessage());
	return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}

package com.task.spring.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="parent")
public class ParentTask {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;
	private String ptask;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPtask() {
		return ptask;
	}
	public void setPtask(String ptask) {
		this.ptask = ptask;
	}
	
	
	
}

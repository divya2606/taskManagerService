package com.task.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.task.spring.model.TaskManager;
import com.task.spring.service.TaskManagerService;

@Controller
@RequestMapping(value="Tasks")
public class TaskManagerMvcController {
	
	@Autowired
	private TaskManagerService  taskManagerService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView welcomePage(HttpServletRequest request) {
		request.setAttribute("currentTab", "add");
		ModelAndView model = new ModelAndView("home");
	    model.addObject("tasksForm", new TaskManager()); // the Category object is used as a template to generate the form
	    return model;
	}
	
	@RequestMapping(value = "cancel", method = RequestMethod.GET)
	public ModelAndView cancel(HttpServletRequest request) {
		request.setAttribute("currentTab", "view");
		ModelAndView model = new ModelAndView("home");
	    List<TaskManager> list= taskManagerService.view("", "", "", "", "", "");
		model.addObject("list",list);
		model.addObject("tasksForm", new TaskManager());
	    return model;
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("tasksForm") TaskManager taskmanager,@RequestParam("task") String task) {
		taskManagerService.save(taskmanager);
		return new ModelAndView("redirect:/Tasks/list");
	}
	
	@RequestMapping(value="delete/{taskId}", method=RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request,@PathVariable("taskId") int taskId) {
		taskManagerService.delete(taskId);
		ModelAndView model = new ModelAndView("home");
	    request.setAttribute("currentTab", "view");
		List<TaskManager> list= taskManagerService.view("", "", "", "", "", "");
		model.addObject("list",list);
		model.addObject("tasksForm", new TaskManager());
	
	    return model;
		//return new ModelAndView("redirect:/Tasks/list");
	}
	
	@RequestMapping(value="update/{taskId}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable("taskId") int taskId) {
		
		ModelAndView model = new ModelAndView("update");
		TaskManager task =taskManagerService.get(taskId);
		model.addObject("tasksForm",task);
		return model;
	}
	
	@RequestMapping(value="updateTaskURL", method=RequestMethod.GET)
	public ModelAndView updateTaskURL(HttpServletRequest request,Model model,@RequestParam(value="taskId") int taskId,@RequestParam(value="task") String task,@RequestParam(value="parentId") String parentId,
			@RequestParam(value="priority") int priority,@RequestParam(value="startDate") String startDate,
			@RequestParam(value="endDate") String endDate) throws ParseException {
		
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate=format1.parse(startDate);
		Date edate=format1.parse(endDate);
		TaskManager taskM = new TaskManager();
		taskM.setEndDate(edate);
		taskM.setParentId(parentId);
		taskM.setTask(task);
		taskM.setPriority(priority);
		taskM.setStartDate(sdate);
		request.setAttribute("currentTab", "view");
		taskManagerService.update(taskId,taskM);
		ModelAndView model1 = new ModelAndView("home");
		request.setAttribute("currentTab", "view");
		List<TaskManager> list= taskManagerService.view("", "", "", "", "", "");
		model1.addObject("list",list);
		model1.addObject("tasksForm", new TaskManager());
		return model1;
	}
	
	@RequestMapping(value="view", method=RequestMethod.POST)
	public ModelAndView view(HttpServletRequest request,Model model1 ,@RequestParam(value="task",required = false) String task,@RequestParam(value="parentId",required = false) String parentId,@RequestParam(value="PriorityFrom", required = false) String PriorityFrom
			,@RequestParam(value="Priorityto",required = false) String Priorityto,@RequestParam(value="startDate",required = false) String startDate,@RequestParam(value="endDate",required = false) String endDate) {
		ModelAndView model = new ModelAndView("home");
		request.setAttribute("currentTab", "view");
		List<TaskManager> list= taskManagerService.view(task, parentId, PriorityFrom, Priorityto, startDate, endDate);
		model.addObject("list",list);
		model.addObject("tasksForm", new TaskManager());
		return model;
	}
	
	@RequestMapping(value="viewAll", method=RequestMethod.GET)
	public ModelAndView viewAll(HttpServletRequest request,Model model1) {
		ModelAndView model = new ModelAndView("home");
		request.setAttribute("currentTab", "view");
		String task="";
		String parentId="";
		String PriorityFrom="";
		String Priorityto="";
		String startDate="";
		String endDate="";
		List<TaskManager> list= taskManagerService.view(task, parentId, PriorityFrom, Priorityto, startDate, endDate);
		model.addObject("list",list);
		model.addObject("tasksForm", new TaskManager());
		return model;
	}

}

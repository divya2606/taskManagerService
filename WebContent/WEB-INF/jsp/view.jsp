<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Task Manager</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="/WEB-INF/styles.css">
	</head>

	<body>

		<div class="container">
			<h1>Task Manager</h1>
		</div>
	
		<div id="tabForm" class="container">
			<ul class="nav nav-pills">
				<li class="active"><a href="#addPane" data-toggle="tab">Add Task</a></li>
				<li><a href="#viewPane" data-toggle="tab">View Task</a></li>
			</ul>
			<spring:url value="/Tasks/save" var="saveURL"/>
			<div class="tab-content">
			<div class="tab-pane active" id="addPane">
			<form:form action="${saveURL}" method="POST" modelAttribute="tasksForm" >
			<form:hidden path="taskId"/>
			
				
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="task">Task:</label>
							<div class="col-xs-4">
							<form:input path="task" />
								
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="priority">Priority:</label>
							<div class="col-xs-4">
								<form:input path="priority"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="ParentTask">Parent Task:</label>
							<div class="col-xs-4">
								<form:input path="parentId" />
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="StartDate">Start	Date:</label>
							<div class="col-xs-4">
								<form:input path="startDate" />
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="EndDate">End Date:</label>
							<div class="col-xs-4">
								<form:input path="endDate"/>
							</div>
						</div>
	
						<div class="form-group row">
							<div class="col-sm-offset-2 col-xs-4">
								<button type="submit" class="btn defaultButton">AddTask</button>
								<button type="submit" class="btn defaultButton">Reset</button>
							</div>
						</div>
					</form>
				
			</form:form>
			</div>
			
				<spring:url value="/Tasks/view" var="viewURL"/>
			<%-- <form:form action="${viewURL}" method="POST"  modelAttribute="tasksForm" > --%>
			<div class="tab-pane" id="viewPane">
			
			<form:form action="${viewURL}" method="POST"  modelAttribute="tasksForm" > 
				
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Task:</label>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control defaultTexBox" id="Task"	name="Task" /> -->
								<form:input path="task" class="form-control defaultTexBox" name="task" value="" />
							</div>
							<label class="col-xs-1 defaultLabel">Parent Task:</label>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control defaultTexBox" id="ParentTask" name="ParentTask" /> -->
								<form:input path="parentId" class="form-control defaultTexBox" name="parentId" value=""/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Priority From:</label>
							<div class="col-xs-2">
								<!-- <input type="text" class="form-control defaultTexBox" id="PriorityFrom" name="PriorityFrom" /> -->
								<form:input path="PriorityFrom" class="form-control defaultTexBox" name="PriorityFrom" value="" />
							</div>
							<label class="col-xs-1 defaultLabel">Priority To:</label>
							<div class="col-xs-2">
								<!-- <input type="text" class="form-control defaultTexBox" id="PriorityTo" name="PriorityTo" /> -->
								<form:input path="Priorityto" class="form-control defaultTexBox" name="Priorityto" value="" />
							</div>
							<label class="col-xs-1 defaultLabel">Start Date:</label>
							<div class="col-xs-2">
								<!-- <input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="StartDate" name="StartDate" /> -->
								<form:input path="startDate" class="form-control defaultTexBox" name="startDate" value="" />
							</div>
							<label class="col-xs-1 defaultLabel">End Date:</label>
							<div class="col-xs-2">
								<!-- <input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="EndDate" name="EndDate" /> -->
								<form:input path="endDate" class="form-control defaultTexBox" name="endDate" value=""/>
							</div>
						</div>
	
						<div class="form-group row">
							<div class="col-sm-offset-2 col-xs-4">
								<button type="submit" class="btn defaultButton">Search</button>
							</div>
						</div>
						<hr class="style6" />
	
						<table class="table" id="myTable">
							<tbody>
								<tr>
									<th class="col-xs-3 defaultLabel">Task</th>
									<th class="col-xs-4 defaultLabel">Parent</th>
									<th class="col-xs-1 defaultLabel">Priority</th>
									<th class="col-xs-1 defaultLabel">Start</th>
									<th class="col-xs-1 defaultLabel">End</th>
									<th class="col-xs-offset-1"></th>
									<th class="col-xs-offset-1"></th>
								</tr>
	<!-- 
								<tr height="100px">
									<td class="col-xs-3"><p>Task1</p></td>
									<td class="col-xs-3"><p>Parent Task1</p></td>
									<td class="col-xs-1">10</td>
									<td class="col-xs-2">20/10/2017</td>
									<td class="col-xs-2">21/10/2017</td>
									<td class="col-xs-1"><a href="UpdateTask.html" class="btn defaultButton">Edit</button></td>
									<td class="col-xs-1"><button type="submit" class="btn defaultButton">End Task</button></td>
								</tr> -->
								<c:if test="${not empty list}">
								<c:forEach items="${list}" var="TaskManager">
								<tr>
								
							 	<td>${TaskManager.task}</td> 
								<td>${TaskManager.parentId}</td>
								<td>${TaskManager.priority}</td>
								<td>${TaskManager.startDate}</td>
								<td>${TaskManager.endDate}</td>
								<td>
								<spring:url value="/Tasks/update/${TaskManager.taskId}" var ="updateURL"></spring:url>
								<a href ="${updateURL}">UPDATE</a>
								</td>
								
								<td>
								<spring:url value="/Tasks/delete/${TaskManager.taskId}" var ="deleteURL"></spring:url>
								<a href ="${deleteURL}">DELETE</a>
								</td>
								</tr>
								
								</c:forEach>
								</c:if>
								
							</tbody>
						</table>
					</form>
					
				
				</form:form>
				</div>
			</div>
		</div>

	</body>
</html>


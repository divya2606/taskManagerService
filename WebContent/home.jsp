<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
	<spring:url value="/Tasks/save" var="saveURL"/>
	<form:form action="${saveURL}" method="POST" modelAttribute="tasksForm">
	<form:hidden path="taskId"/>
	
		<div class="container">
			<h1>Task Manager</h1>
		</div>
	
		<div id="tabForm" class="container">
			<ul class="nav nav-pills">
				<li class="active"><a href="#addPane" data-toggle="tab">Add Task</a></li>-----------------------
				<li><a href="#viewPane" data-toggle="tab">View Task</a></li>
			</ul>
	
			<div class="tab-content">
				<div class="tab-pane active" id="addPane">
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="task">Task:</label>
							<div class="col-xs-4">
							<form:input path="Task"/>
								
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
								<form:input path="parentId"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="StartDate">Start	Date:</label>
							<div class="col-xs-4">
								<form:input path="startDate"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="EndDate">End Date:</label>
							<div class="col-xs-4">
								<form:input path="parentId"/>
							</div>
						</div>
	
						<div class="form-group row">
							<div class="col-sm-offset-2 col-xs-4">
								<button type="submit" class="btn defaultButton">AddTask</button>
								<button type="submit" class="btn defaultButton">Reset</button>
							</div>
						</div>
					</form>
				</div>
	
				<div class="tab-pane" id="viewPane">
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Task:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control defaultTexBox" id="Task"	name="Task" />
							</div>
							<label class="col-xs-1 defaultLabel">Parent Task:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control defaultTexBox" id="ParentTask" name="ParentTask" />
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Priority From:</label>
							<div class="col-xs-2">
								<input type="text" class="form-control defaultTexBox" id="PriorityFrom" name="PriorityFrom" />
							</div>
							<label class="col-xs-1 defaultLabel">Priority To:</label>
							<div class="col-xs-2">
								<input type="text" class="form-control defaultTexBox" id="PriorityTo" name="PriorityTo" />
							</div>
							<label class="col-xs-1 defaultLabel">Start Date:</label>
							<div class="col-xs-2">
								<input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="StartDate" name="StartDate" />
							</div>
							<label class="col-xs-1 defaultLabel">End Date:</label>
							<div class="col-xs-2">
								<input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="EndDate" name="EndDate" />
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
	
								<tr height="100px">
									<td class="col-xs-3"><p>Task1</p></td>
									<td class="col-xs-3"><p>Parent Task1</p></td>
									<td class="col-xs-1">10</td>
									<td class="col-xs-2">20/10/2017</td>
									<td class="col-xs-2">21/10/2017</td>
									<td class="col-xs-1"><a href="UpdateTask.html" class="btn defaultButton">Edit</button></td>
									<td class="col-xs-1"><button type="submit" class="btn defaultButton">End Task</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		</form:form>
	</body>
</html>


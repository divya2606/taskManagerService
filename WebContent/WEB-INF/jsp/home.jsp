<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
 String tabActive =  (String)request.getAttribute("currentTab");
 String tab1 = "";
 String tab2 = "";
 if(tabActive.equalsIgnoreCase("add")){
	 tab1 = "active";
	 tab2 = "";
 }
 else{
	 tab1 = "";
	 tab2 = "active";

 }
  %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Task Manager</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<style><%@include file="/WEB-INF/styles.css"%></style>

	</head>

	<body>

		<div class="container">
			<h1>Task Manager</h1>
		</div>
	
		<div id="tabForm" class="container">
			<ul class="nav nav-pills">
			<!-- class="active" -->
				<li class="<%=tab1%>"><a href="#addPane" data-toggle="pill">Add Task</a></li>
				
				<%-- <form:form action="${viewURL}" method="POST" modelAttribute="tasksForm" > --%>
				<!-- <li><a href="#viewPane" data-toggle="tab">View Task</a></li> -->
				<%-- </form:form> --%>
				<spring:url value="/Tasks/viewAll" var="viewAllURL"/>
				<li class="<%=tab2%>"><a href="${viewAllURL}" >View Task </a></li>
			</ul>
			<spring:url value="/Tasks/save" var="saveURL"/>
			<div class="tab-content">
			<div class="tab-pane <%=tab1%>" id="addPane">
			<form:form action="${saveURL}" method="POST" modelAttribute="tasksForm" >
			<form:hidden path="taskId"/>
			
				
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="task">Task:</label>
							<div class="col-xs-4">
							<form:input path="task" class="form-control defaultTexBox" maxlength="20" required="required" />
								
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="priority">Priority:</label>
							<div class="col-xs-4">
							<span id="slider_min_value" class="rangeMin">0</span> 
							<span id="slider_max_value" class="rangeMax">30</span>
								<form:input path="priority" type="range" class="slider" min="0" max="30" step="1" data-show-value="true" required="required"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="ParentTask">Parent Task:</label>
							<div class="col-xs-4">
								<form:input path="parentId" class="form-control defaultTexBox" maxlength="50" required="required"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="StartDate">Start	Date:</label>
							<div class="col-xs-4">
								<form:input path="startDate"  type="text"  class="form-control defaultTexBox" required="required" placeholder="mm/dd/yyyy" />
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-2 defaultLabel" for="EndDate">End Date:</label>
							<div class="col-xs-4">
								<form:input path="endDate"  type="text"  class="form-control defaultTexBox" required="required" placeholder="mm/dd/yyyy"/>
							</div>
						</div>
	
						<div class="form-group row">
							<div class="col-sm-offset-2 col-xs-4">
								<button type="submit" class="btn defaultButton">AddTask</button>
								<button type="reset" class="btn defaultButton">Reset</button>
							</div>
						</div>
					</form>
				
			</form:form>
			</div>
			
				<spring:url value="/Tasks/view" var="viewURL"/>
			<%-- <form:form action="${viewURL}" method="POST"  modelAttribute="tasksForm" > --%>
			<div class="tab-pane <%=tab2%>" id="viewPane">
			
			<form:form action="${viewURL}" method="POST"  modelAttribute="tasksForm" > 
				
					<form class="form-horizontal">
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Task:</label>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control defaultTexBox" id="Task"	name="Task" /> -->
								<form:input path="task" class="form-control defaultTexBox" name="task" value="" id="myTask"/>
							</div>
							<label class="col-xs-1 defaultLabel">Parent Task:</label>
							<div class="col-xs-5">
								<!-- <input type="text" class="form-control defaultTexBox" id="ParentTask" name="ParentTask" /> -->
								<form:input path="parentId" class="form-control defaultTexBox" name="parentId" value="" id="myParent"/>
							</div>
						</div>
	
						<div class="form-group row">
							<label class="col-xs-1 defaultLabel">Priority From:</label>
							<div class="col-xs-2">
								<!-- <input type="text" class="form-control defaultTexBox" id="PriorityFrom" name="PriorityFrom" /> -->
								<form:input path="PriorityFrom" class="form-control defaultTexBox" name="PriorityFrom" value="" id="myPriorityFrom"/>
							</div>
							<label class="col-xs-1 defaultLabel">Priority To:</label>
							<div class="col-xs-2">
								<!-- <input type="text" class="form-control defaultTexBox" id="PriorityTo" name="PriorityTo" /> -->
								<form:input path="Priorityto" class="form-control defaultTexBox" name="Priorityto" value="" id="myPriorityTo"/>
							</div>
							<label class="col-xs-1 defaultLabel">Start Date:</label>
							<div class="col-xs-2">
								<!-- <input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="StartDate" name="StartDate" /> -->
								<form:input path="startDate" class="form-control defaultTexBox" name="startDate" value="" id="myStartDate"/>
							</div>
							<label class="col-xs-1 defaultLabel">End Date:</label>
							<div class="col-xs-2">
								<!-- <input type="text" onfocus="(this.type='date')"	onfocusout="(this.type='text')"	class="form-control defaultTexBox" id="EndDate" name="EndDate" /> -->
								<form:input path="endDate" class="form-control defaultTexBox" name="endDate" value="" id="myEndDate"/>
							</div>
						</div>
	<!-- 
						<div class="form-group row">
							<div class="col-sm-offset-2 col-xs-4">
								<button type="submit" class="btn defaultButton">Search</button>
							</div>
						</div> -->
						<hr class="style6" />
	
		</form>
					
				</form:form>
				
						<table class="table" id="myTable">
						<thead>
									<th class="col-xs-3 defaultLabel">Task</th>
									<th class="col-xs-4 defaultLabel">Parent</th>
									<th class="col-xs-1 defaultLabel">Priority</th>
									<th class="col-xs-1 defaultLabel">Start</th>
									<th class="col-xs-1 defaultLabel">End</th>
									<th class="col-xs-offset-1"></th>
									<th class="col-xs-offset-1"></th>
						</thead>
							<tbody id="myBody">
								<c:if test="${not empty list}">
								<c:forEach items="${list}" var="TaskManager">
								<tr>
								
							 	<td>${TaskManager.task}</td> 
								<td>${TaskManager.parentId}</td>
								<td>${TaskManager.priority}</td>
								<td>${TaskManager.startDate}</td>
								<td>${TaskManager.endDate}</td>
								<%-- <td>
								
								<spring:url value="/Tasks/update/${TaskManager.taskId}" var ="updateURL"></spring:url>
								 <a href ="${updateURL}" type="submit" >UPDATE</a> 
								
								</td> --%>
								
								<td>
								<spring:url value="/Tasks/update/${TaskManager.taskId}" var="updateURL"/>
			
					
								<form:form action="${updateURL}" method="GET"  modelAttribute="tasksForm" > 
									<button type="submit" class="btn defaultButton">Edit</button>
								</form:form>
								</td>
								<%-- 
								<td>
								<spring:url value="/Tasks/delete/${TaskManager.taskId}" var ="deleteURL"></spring:url>
								<a href ="${deleteURL}">DELETE</a>
								</td> --%>
								<td>
							<spring:url value="/Tasks/delete/${TaskManager.taskId}" var="deleteURL"/>
								<form:form action="${deleteURL}" method="GET"  modelAttribute="tasksForm" > 
									<button type="submit" class="btn defaultButton">End Task</button>
								</form:form>
								</td>
								</tr>
								
								</c:forEach>
								</c:if>
								
							</tbody>
						</table>
				
				</div>
			</div>
		</div>
		<script>
$(document).ready(function(){
  $("#myTask").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myBody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
  
  $("#myParent").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myBody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });

  $("#myPriorityFrom").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myBody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
  
  $("#myPriorityTo").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myBody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
  
  $("#myStartDate").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myBody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });

$("#myEndDate").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#myBody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
	</body>
</html>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">

<head>

<title>Task Manager</title>

<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style><%@include file="/WEB-INF/styles.css"%></style>

</head>

<body>

<spring:url value="/Tasks/updateTaskURL" var="updateTaskURL"/>
			
			<form:form action="${updateTaskURL}" method="GET" modelAttribute="tasksForm" >
			
	<div class="container">

		<h1>Task Manager</h1>

		<form class="form-horizontal">
	<form:hidden path="taskId"/>
			<div class="form-group row">

				<label class="col-xs-2 defaultLabel" for="task">Task:</label>

				<div class="col-xs-4">

					<!-- <input type="text" class="form-control defaultTexBox" id="task" name="task"> -->
					<form:input path="task" class="form-control defaultTexBox" id="task" name="task" required="required"/>

				</div>

			</div>

			<div class="form-group row">

				<label class="col-xs-2 defaultLabel" for="priority">Priority:</label>

				<div class="col-xs-4">
 
				<span id="slider_min_value" class="rangeMin">0</span> 
							<span id="slider_max_value" class="rangeMax">30</span>
								<form:input path="priority" type="range" class="slider" min="0" max="30" step="1" required="required"/>
							</div>
			</div>

			<div class="form-group row">

				<label class="col-xs-2 defaultLabel" for="ParentTask">Parent
					Task:</label>

				<div class="col-xs-4">
<!-- 
					<input type="text" class="form-control defaultTexBox"
						id="ParentTask" name="ParentTask" /> -->
						
						<form:input path="parentId" class="form-control defaultTexBox" id="parentId" name="parentId" required="required" maxlength="50"/>

				</div>

			</div>

			<div class="form-group row">

				<label class="col-xs-2 defaultLabel" for="StartDate">Start
					Date:</label>

				<div class="col-xs-4">

					<!-- <input type="text" onfocus="(this.type='date')"
						onfocusout="(this.type='text')" class="form-control defaultTexBox"
						id="StartDate" name="StartDate" />
 -->
 	<form:input path="startDate" class="form-control defaultTexBox" id="startDate" name="startDate" required="required" maxlength="10"/>
				</div>

			</div>

			<div class="form-group row">

				<label class="col-xs-2 defaultLabel" for="EndDate">End Date:</label>

				<div class="col-xs-4">
		<form:input path="endDate" class="form-control defaultTexBox" id="endDate" name="endDate" required="required" />

				</div>

			</div>

			<div class="form-group row">

				<div class="col-sm-offset-2 col-xs-4">

					<button type="submit" class="btn defaultButton">Update</button>

			<spring:url value="/Tasks/cancel" var="cancelTaskURL"/>
			
			<form:form action="${cancelTaskURL}" method="GET" modelAttribute="tasksForm" >
					 <button type="submit" class="btn defaultButton">Cancel</button>
			</form:form>
				</div>

			</div>

		</form>

	</div>


</form:form>
</body>

</html>

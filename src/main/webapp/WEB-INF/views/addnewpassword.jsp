<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Password</title>
	<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
 	<div class="generic-container">

		<div class="well lead">Password Form</div>
	 	<form:form method="POST" modelAttribute="passwords" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="system">System</label>
					<div class="col-md-7">
						<form:input type="text" path="system" id="system" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="system" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="user">User</label>
					<div class="col-md-7">
						<form:input type="text" path="user" id="user" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="user" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="text" path="password" id="password" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<form:input type="hidden" path="account" id="account" value="${loggedinuser}"/>
	
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/passwords' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Password</title>
 
<style>
 
    .error {
        color: #ff0000;
    }
</style>
 
</head>
 
<body>
 
    <h2>Edit Password</h2>
  
    <form:form method="POST" modelAttribute="passwords">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td><label for="system">System: </label> </td>
                <td><form:input path="system" id="system"/></td>
                <td><form:errors path="system" cssClass="error"/></td>
            </tr>
         
            <tr>
                <td><label for="user">User: </label> </td>
                <td><form:input path="user" id="user"/></td>
                <td><form:errors path="user" cssClass="error"/></td>
            </tr>
     		
     		<tr>
                <td><label for="password">Password: </label> </td>
                <td><form:input path="password" id="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
     		<form:input type="hidden" path="account" id="account" value="${loggedinuser}"/>
            <tr>
                <td>
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>

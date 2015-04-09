<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>Update the user</title>
	</head>
	<body>
		<h3 id="top">Update the user</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<spring-form:form commandName="user" method="POST">
			<%-- JSR-303 Validation --%>
			<spring-form:errors path="*" cssClass="errorBlock" element="div" />
			<table>
				<tr>
					<td>Title</td>
					<td><spring-form:input path="name" autofocus="on" autocomplete="on" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><spring-form:input path="description" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td>E-mail</td>
					<td><spring-form:input path="email" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Image</td>
					<td><spring-form:input path="imageURL" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="imageURL" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Birthday</td>
					<td><spring-form:input path="birthday" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="birthday" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3">
						<spring-form:button>Update</spring-form:button>
					</td>
				</tr>
				</table>
		</spring-form:form>
	</body>
</html>
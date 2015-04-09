<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>Sign in</title>
	</head>
	<body>
		<h3 id="top">Sign in</h3>
		
		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<spring-form:form commandName="user" method="POST">
			<%-- JSR-303 Validation --%>
			<spring-form:errors path="*" cssClass="errorBlock" element="div" />
			<table>
				<tr>
					<td>Name</td>
					<td><spring-form:input path="name" autofocus="on" autocomplete="on" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><spring-form:password path="description" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="description" cssClass="error" /></td>
				</tr>
				
				<tr>
					<td>Keep signed in</td>
					<td><input type="checkbox" name="keepSignedIn" /></td>
				</tr>
				<tr>
					<td colspan="3">
						<spring-form:button>Sign in</spring-form:button>
					</td>
				</tr>
				</table>
		</spring-form:form>
	</body>
</html>
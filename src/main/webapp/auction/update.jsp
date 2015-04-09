<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>Update the auction</title>
	</head>
	<body>
		<h3 id="top">Update the auction</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<spring-form:form commandName="auction" method="POST">
			<%-- JSR-303 Validation --%>
			<spring-form:errors path="*" cssClass="errorBlock" element="div" />
			<table>
				<tr>
					<td>Title</td>
					<td><spring-form:input path="title" autofocus="on" autocomplete="on" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="title" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><spring-form:input path="description" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="description" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Image</td>
					<td><spring-form:input path="imageURL" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="imageURL" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Start time</td>
					<td><spring-form:input path="startTime" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="startTime" cssClass="error" /></td>
				</tr>
				<tr>
					<td>End time</td>
					<td><spring-form:input path="endTime" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="endTime" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Starting price</td>
					<td><spring-form:input path="startPrice" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="startPrice" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Current price</td>
					<td><spring-form:input path="currentPrice" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="currentPrice" cssClass="error" /></td>
				</tr>
				<tr>
					<td></td>
					<td><spring-form:checkbox path="active" label="Activate now" /></td>
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
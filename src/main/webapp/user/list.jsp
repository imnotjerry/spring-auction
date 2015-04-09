<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>User list</title>
	</head>
	<body>
		<h3 id="top">User list</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<c:if test="${empty users}">
			<p>No users.</p>
			<% System.out.println("No users");%>
		</c:if>

		<c:forEach var="user" items="${users}">
			<div class="auctionLot">

				<table>
					<tr>
						<th>ID: ${user.id}</th>
						<th>${user.name}</th>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<a href="user.cgi?id=${user.id}" title="Details">Details</a>
							<a href="update.cgi?id=${user.id}" title="Edit">Edit</a>
							<a href="delete.cgi?id=${user.id}" onclick="return confirm('Delete user?')" title="Delete">Delete</a>
						</td>
					</tr>
					<tr>
						<td>Description</td>
						<td><a href="user.cgi?id=${user.id}" title="${user.name}" style="text-decoration: none">
								<%-- Show a short description... --%>
								<c:set var="MAX" value="16"/>
								<c:if test="${user.description.length() ge MAX}">
									${user.description.substring(0, MAX)}...
								</c:if>
								<c:if test="${user.description.length() lt MAX}">
									${user.description}
								</c:if>
							</a>
						</td>
					</tr>
					<tr>
						<td>Image</td>
						<td><a href="${user.imageURL}" title="${user.name}">
								<img src="${user.imageURL}" alt="[IMAGE] ${user.name}" class="userImage" /></a>
						</td>
					</tr>
					<tr>
						<td>Birthday</td>
						<td><fmt:formatDate value="${user.birthday}" type="both" timeStyle="SHORT" /></td>
					</tr>
					<tr>
						<td>Registration date</td>
						<td><fmt:formatDate value="${user.regDate}" type="both" timeStyle="SHORT" /></td>
					</tr>
					<tr>
						<td>Last activity</td>
						<td><fmt:formatDate value="${user.lastActivity}" type="both" timeStyle="SHORT" /></td>
					</tr>
				</table>


			</div>
		</c:forEach>

		<a href="#top" title="Top">Top</a>
	</body>
</html>
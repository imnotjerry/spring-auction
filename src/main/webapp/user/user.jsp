<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>${user.name} - User</title>
	</head>
	<body>
		<h3 id="top">${user.name} - User</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<div id="userInfo" class="auctionLot">
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
					<td>${user.description}</td>
				</tr>
				<tr>
					<td>Image</td>
					<td><a href="${user.imageURL}" title="${user.name}">
							<img src="${user.imageURL}" alt="[IMAGE] ${user.name}" class="userImage" /></a>
					</td>
				</tr>
				<tr>
					<td>E-mail</td>
					<td>${user.email}</td>
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
				<tr>
					<td>Number of visits</td>
					<td>${user.statistics.nVisits}</td>
				</tr>
			</table>
		</div>

		<a href="#top" title="Top">Top</a>
	</body>
</html>
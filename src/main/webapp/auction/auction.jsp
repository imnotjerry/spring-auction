<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring-form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<c:if test="${empty auction}">
	<p>No auction is active at this time.</p>
	<% System.err.println("Auction: null");%>
	<c:redirect url="list.cgi" />
</c:if>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>${auction.title} - Auction</title>
	</head>
	<body>
		<h3 id="top">${auction.title} - Auction</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<div class="auctionLot">
			<table>
				<tr>
					<th>Lot #${auction.id}</th>
					<th>${auction.title}</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<a href="auction.cgi?id=${auction.id}" title="Details">Details</a>
						<a href="update.cgi?id=${auction.id}" title="Edit">Edit</a>
						<a href="delete.cgi?id=${auction.id}" onclick="return confirm('Delete auction?')" title="Delete">Delete</a>
					</td>
				</tr>
				<tr>
					<td>Description</td>
					<td>${auction.description}</td>
				</tr>
				<tr>
					<td>Image</td>
					<td><a href="${auction.imageURL}" title="${auction.title}">
							<img src="${auction.imageURL}" alt="[IMAGE] ${auction.title}" class="auctionImage" /></a>
					</td>
				</tr>
				<tr>
					<td>Start time</td>
					<td><fmt:formatDate value="${auction.startTime}" type="both" timeStyle="SHORT" /></td>
				</tr>
				<tr>
					<td>End time</td>
					<td><fmt:formatDate value="${auction.endTime}" type="both" timeStyle="SHORT" /></td>
				</tr>
				<tr>
					<td>Starting price</td>
					<td><fmt:formatNumber value="${auction.startPrice}" type="currency" /></td>
				</tr>
				<tr>
					<td>Current price</td>
					<td><fmt:formatNumber value="${auction.currentPrice}" type="currency" /></td>
				</tr>
				<tr>
					<td>User</td>
					<td>${user}</td>
				</tr>
			</table>
		</div>

		<c:if test="${empty bid}">
			<p>Auction is not active.</p>
			<% System.err.println("Auction is not active");%>
		</c:if>
			
		<c:if test="${!empty bid}">
		<spring-form:form commandName="bid" method="POST">
			<%-- JSR-303 Validation --%>
			<spring-form:errors path="*" cssClass="errorBlock" element="div" />
			<table>
				<tr>
					<td>Make a bid</td>
					<td><spring-form:input path="amount" cssErrorClass="errorBlock" /></td>
					<td><spring-form:errors path="amount" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3">
						<spring-form:button>Make bid</spring-form:button>
						</td>
					</tr>
				</table>
		</spring-form:form>
		</c:if>

		<a href="#top" title="Top">Top</a>
	</body>
</html>
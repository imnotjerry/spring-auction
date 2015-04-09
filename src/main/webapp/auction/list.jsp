<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link type="text/css" rel="stylesheet" href="../auction.css" />
		<title>Auction list</title>
	</head>
	<body>
		<h3 id="top">Auction list</h3>

		<%@include file="../WEB-INF/jspf/navigation.jspf" %>

		<c:if test="${empty auctions}">
			<p>No auction is active at this time.</p>
			<% System.out.println("No auctions");%>
		</c:if>

		<c:forEach var="auction" items="${auctions}">
			<div id="auctionLot" class="auctionLot">



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
						<td>
							<%-- Show a short description... --%>
							<c:set var="MAX" value="16"/>
							<c:if test="${auction.description.length() ge MAX}">
								${auction.description.substring(0, MAX)}...
								<a href="auction.cgi?id=${auction.id}" title="${auction.title}">[More]</a>
							</c:if>
							<c:if test="${auction.description.length() lt MAX}">
								${auction.description}
							</c:if>
						</td>
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
				</table>


			</div>
		</c:forEach>

		<a href="#top" title="Top">Top</a>
	</body>
</html>
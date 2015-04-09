<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link type="text/css" rel="stylesheet" href="auction.css" />
		<title>Spring Auction</title>
    </head>
    <body>
		<h2>Spring Auction</h2>
		<h3>Spring MVC application</h3>
		<%@include file="WEB-INF/jspf/navigation.jspf" %>
		
		<p><c:out value="User: ${sessionScope.userId}" /></p>

	</body>
</html>

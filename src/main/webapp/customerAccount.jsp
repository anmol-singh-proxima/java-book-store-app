<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.onlineBookStore.dao.BookOrder" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Book Store | Customer Account</title>
<style>

/*
======== Main Style ========
*/

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
body {
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	min-height: 100vh;
	font-size: 16px;
	font-family: 'Sans serif';
	max-width: 100vw;
}
ul {
	list-style-type: none;
}
a {
	text-decoration: none;
}



/*
======== Header Style ========
*/

#header {
	display: grid;
	grid-template-columns: repeat(12, 1fr);
	grid-template-rows: auto;
	max-width: 100%;
	height: 50px;
	padding: 0 100px;
	background-color: rgba(150, 76, 36, 1);
}

#header h2 {
	grid-column: 1/5;
	grid-row: 1/2;
}
#header h2 a {
	line-height: 50px;
	color: rgba(252, 252, 252, 1);
}

#header form {
	grid-column: 5/9;
	grid-row: 1/2;
	line-height: 50px;
}
#header form input, #header form button {
	font-size: 1rem;
	border: 1px solid #787878;
	border-radius: 0px;
	padding: 5px 15px;
	outline: none;
}
#header form button {
	background-color: rgba(75, 90, 191, 1);
	color: rgba(245, 245, 245, 1);
}
#header form button:hover {
	background-color: rgba(245, 245, 245, 1);
	color: rgba(75, 90, 191, 1);
}

#header ul {
	grid-column: 9/13;
	grid-row: 1/2;
	text-align: right;
}
#header ul li {
	display: inline-block;
}
#header ul li a {
	display: inline-block;
	font-size: 1rem;
	line-height: 50px;
	padding: 0px 12px;
	color: rgba(242, 231, 225, 1);
}



/*
======== Section Style ========
*/

#content {
	max-width: 100%;
	padding: 30px 100px;
	display: grid;
	grid-template-columns: repeat(12, 1fr);
	grid-template-rows: auto;
}

#profile {
	grid-column: 1/7;
	grid-row: 1/2;
}
#profile table {
	margin: 10px 20px 10px 0;
	text-align: left;
	border-collapse:collapse;
}
#profile table th, #profile table td {
	text-align: left;
	padding: 6px 10px;
	border: 1px solid #787878;
}

#orders {
	grid-column: 7/13;
	grid-row: 1/2;
}
#orders table {
	margin: 10px 20px 10px 0;
	text-align: left;
	border-collapse:collapse;
}
#orders table th, #orders table td {
	text-align: left;
	padding: 6px 10px;
	border: 1px solid #787878;
}



/*
======== Footer Style ========
*/

#footer {
	display: grid;
	grid-template-columns: repeat(12, 1fr);
	grid-template-rows: auto;
	max-width: 100%;
	padding: 10px 100px;
	background-color: #333;
}

#footer ul {
	grid-column: 1/7;
	grid-row: 1/2;
	display: block;
}
#footer ul li {
	display: inline-block;
}
#footer ul li a {
	display: inline-block;
	font-size: 0.95rem;
	line-height: 1.2;
	padding: 0px 12px;
	padding-left: 0px;
	color: #efefef;
}

#footer p {
	grid-column: 7/13;
	grid-row: 1/2;
	text-align: right;
	color: #a8a8a8;
}


</style>
</head>
<body>

	<%

	// Ways to solve back button problem
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	response.setHeader("Expires", "0"); // Proxies
	
	if(session.getAttribute("customer") == null) {
		response.sendRedirect("./login");
	}
	
	%>

	<header id="header">
		<h2><a href="./" >Online Book Store</a></h2>
		<ul>
			<li><a href="./logout">Logout</a></li>
		</ul>
	</header>
	
	<section id="content">
		
		<div id="profile">
			<h2>Profile</h2>
			<table>
				<tr>
					<td><b>Name</b></td>
					<td>${customer.name}</td>
				</tr>
				<tr>
					<td><b>Email</b></td>
					<td>${customer.email}</td>
				</tr>
				<tr>
					<td><b>Phone</b></td>
					<td>${customer.phone}</td>
				</tr>
				<tr>
					<td><b>Address</b></td>
					<td>${customer.address}</td>
				</tr>
			</table>
			<a href="" >Update Profile</a>
		</div>
		
		<div id="orders">
			<h2>Orders</h2>
			<table>
				<tr>
					<th>Order Id</th>
					<th>Order Details</th>
				</tr>
				<c:forEach var="order" items="${listOfOrders}">
					<tr>
						<td>${order.orderId}</td>
						<td>
							<p><b>Book Title: </b>${order.book.bookName}</p>
							<p><b>Price: </b>${order.book.price}</p>
							<p><b>Date: </b>${order.date}</p>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
	</section>
	
	<footer id="footer">
		<ul>
			<li><a href="./registerAsSeller.jsp">Register As Book Seller</a></li>
			<li><a href="">About Us</a></li>
			<li><a href="">Contact Us</a></li>
		</ul>
		<p>All Rights Reserved | Copyright 2021</p>
	</footer>

</body>
</html>

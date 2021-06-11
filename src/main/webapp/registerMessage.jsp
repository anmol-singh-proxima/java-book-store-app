<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Book Store | Customer Registration</title>
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
	padding: 0 100px;
	text-align: center;
}

#content .successMessage {
	font-size: 2rem;
	color: green;
	width: auto;
	padding: 10px 20px;
}
#content .errorMessage {
	font-size: 2rem;
	color: red;
	width: auto;
	padding: 10px 20px;
}
#content a {
	color: DodgerBlue;
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

	<header id="header">
		<h2><a href="./" >Online Book Store</a></h2>
		<ul>
			<c:if test="${customer.customerId != null}">
				<li><a href="./customerAccount">Account</a></li>
				<li><a href="./logout">Logout</a></li>
			</c:if>
			<c:if test="${customer.customerId == null}">
				<li><a href="./login">Login</a></li>
				<li><a href="./customerRegister">Register</a></li>
			</c:if>
		</ul>
	</header>
	
	<section id="content">
		<%
		if(request.getAttribute("error") == null) {
		%>
		<h2 class="successMessage">Your Registration has done Successfully!!</h2>
		<p><a href="./login" style="text-decoration: underline;">Click Here</a> to Login</p>
		<%
		} else {
		%>
		<h2 class="errorMessage">Your Registration has done Successfully!!</h2>
		<p><a href="./customerRegister" style="text-decoration: underline;">Click Here</a> to register Again</p>
		<%
		}
		%>
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

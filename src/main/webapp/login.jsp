<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Book Store | Login</title>
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
}

#loginPage {
	max-width: 100%;
	padding: 0 100px;
}

#loginPage .errorMessage {
	display: block;
	font-size: 1.2rem;
	background-color: rgba(255, 99, 71, 1);
	color: #fff;
	padding: 10px 20px;
	text-align: center;
	width: 400px;
	margin: 20px auto;
}

#loginPage form {
	width: 400px;
	margin: 50px auto;
	border: 1px solid #787878;
	border-radius: 0px;
	padding: 15px;
}
#loginPage form hr {
	display: block;
	margin: 10px 0;
}
#loginPage form h2 {
	font-size: 1.5rem;
	line-height: 1.5;
}
#loginPage form label {
	font-size: 1.1rem;
	line-height: 1.5;
}
#loginPage form input {
	width: 100%;
	margin: 5px 0 10px 0;
	font-size: 1rem;
	line-height: 1.5;
	padding: 6px 12px;
	border: 1px solid #787878;
	border-radius: 0px;
}
#loginPage form input[type="button"] {
	width: auto;
	border: 1px solid #787878;
	border-radius: 0px;
}
#loginPage form p {
	font-size: 0.95rem;
	line-height: 1.5;
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
			<li><a href="./customerRegister">Register</a></li>
		</ul>
	</header>
	
	<section id="content">
	
		<%
		String error = null;
		if(request.getAttribute("error") != null) {
			error = request.getAttribute("error").toString();	
		}
		%>
	
		<div id="loginPage">
			<%
				if(error != null) {
					%>
					<p class="errorMessage">${error}</p>
					<%
				}
			%>
			<form action="./processLogin" method="post">
				<h2>Login</h2>
				<hr>
				<label>Username:</label>
				<input type="email" name="username" placeholder="Enter Email" />
				<label>Password:</label>
				<input type="password" name="password" placeholder="Enter Password" />
				<input type="submit" name="" value="Login" />
				<hr>
				<p>Are you a New User? <a href="./customerRegister">Register Here</a></p>
			</form>
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
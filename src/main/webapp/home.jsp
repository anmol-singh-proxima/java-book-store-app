<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Book Store | Home</title>
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
	padding: 20px 100px;
}

.bookCards {
	width: 100%;
	display: flex;
	flex-wrap: wrap;
}

.bookCard {
	display: block;
	width: 300px;
	border: 1px solid #898989;
	padding: 15px;
	margin: 12px;
}
.bookCard.image {
	display: block;
	min-height: 100px;
	width: 100%;
	background-color: #cd45ef;
	override: hidden;
}
.bookCard.image img {
	display: inline-block;
	max-width: 100%;
	max-height: 300px;
}
.bookCard.image h1 {
	font-size: 2.5rem;
	line-height: 2;
}

.buyNow {
	display: inline-block;
	font-size: 1rem;
	padding: 5px 10px;
	border: 1px solid #6790ab;
	border-radius: opx;
	margin-top: 5px;
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
	
	%>
	
	<header id="header">
		<h2><a href="./" >Online Book Store</a></h2>
		<form action="./searchBooks" method="get">
			<input type="text" name="searchedItem" placeholder="Search Books Here..." />
			<button type="submit" name="submitSearch">Search</button>
		</form>
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
	
		<div class="bookCards">
			<c:forEach var="book" items="${listOfBooks}">
				<div class="bookCard">
					<div class="image">
						<a href="./bookDetails?bookId=${book.bookId}" >
							<img src="https://images.routledge.com/common/jackets/crclarge/978036781/9780367811686.jpg" width="270" height="200" />
						</a>
					</div>
					<h3><a href="./bookDetails?bookId=${book.bookId}" >${book.bookName}</a></h3>
					<p>By <b>${book.authorName}</b></p>
					<p>Published By: <b>${book.publisherHouse}</b></p>
					<p>Category: <b>${book.category}</b></p>
					<p>Rs. <b>${book.price}</b></p>
					<a href="./purchase?bookId=${book.bookId}" class="buyNow">Buy Now</a>
				</div>    
            </c:forEach>
		</div>
		
	</section>
	
	<footer id="footer">
		<ul>
			<li><a href="./bookSellerRegistration">Register As Book Seller</a></li>
			<li><a href="">About Us</a></li>
			<li><a href="">Contact Us</a></li>
		</ul>
		<p>All Rights Reserved | Copyright 2021</p>
	</footer>

</body>
</html>
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

#bookDetails {
	display: grid;
	grid-template-columns: repeat(12, 1fr);
	grid-template-rows: auto;
}
#bookDetails .image {
	grid-column: 1/4;
	grid-row: 1/2;
}
#bookDetails .image img {
	max-width: 100%;
	height: auto;
}

#bookDetails .details {
	grid-column: 4/10;
	grid-row: 1/2;
	margin-left: 20px;
}
#bookDetails .details table {
	margin: 10px 0;
	text-align: left;
	border-collapse:collapse;
}
#bookDetails .details table th, #bookDetails .details table td {
	text-align: left;
	padding: 6px 10px;
	border: 1px solid #787878;
}
#bookDetails .details .buyNow {
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
				<li><a href="./customerRegistration">Register</a></li>
			</c:if>
		</ul>
	</header>
	
	<section id="content">
	
		<div id="bookDetails">
			<div class="image">
				<img src="https://images.routledge.com/common/jackets/crclarge/978036781/9780367811686.jpg" />
			</div>
			<div class="details">
				<h2>Book Details</h2>
				<table>
					<tr> <th>Book Title</th> <td>${book.bookName}</td> </tr>
					<tr> <th>Author Name</th> <td>${book.authorName}</td> </tr>
					<tr> <th>Publisher House</th> <td>${book.publisherHouse}</td> </tr>
					<tr> <th>Year of Publishing</th> <td>${book.yearOfPublishing}</td> </tr>
					<tr> <th>Number of Pages</th> <td>${book.numOfPages}</td> </tr>
					<tr> <th>Category</th> <td>${book.category}</td> </tr>
					<tr> <th>Seller</th> <td>${seller.bookShopName}, ${seller.address}</td> </tr>
					<tr> <th>Books Available</th> <td>${book.stock}</td> </tr>
					<tr> <th>Price</th> <td>Rs. ${book.price}</td> </tr>
				</table>
				<a href="./purchase?bookId=${book.bookId}" class="buyNow">Buy Now</a>
			</div>
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
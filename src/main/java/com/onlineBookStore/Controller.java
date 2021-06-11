package com.onlineBookStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.onlineBookStore.dao.Book;
import com.onlineBookStore.dao.BookDao;
import com.onlineBookStore.dao.BookOrder;
import com.onlineBookStore.dao.BookOrderDao;
import com.onlineBookStore.dao.BookSeller;
import com.onlineBookStore.dao.BookSellerDao;
import com.onlineBookStore.dao.Customer;
import com.onlineBookStore.dao.CustomerDao;
import com.onlineBookStore.dataInstallation.DataInstallation;
import com.onlineBookStore.dataInstallation.DataUninstallation;
import com.onlineBookStore.dbConnection.DatabaseConnection;

@WebServlet("/")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseConnection dbConnection = null;
	private BookDao bookDao;
	private CustomerDao customerDao;
	private BookOrderDao bookOrderDao;
	private BookSellerDao bookSellerDao;
	private DataInstallation installation;
	private DataUninstallation uninstallation;
	private HttpSession session;

	
	public void init() {
		dbConnection = new DatabaseConnection();
		bookDao = new BookDao(dbConnection);
		customerDao = new CustomerDao(dbConnection);
		bookOrderDao = new BookOrderDao(dbConnection);
		bookSellerDao = new BookSellerDao(dbConnection);
		installation = new DataInstallation(dbConnection);
		uninstallation = new DataUninstallation(dbConnection);
	}
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/install":
				this.installData(request, response);
				break;
			case "/uninstall":
				this.uninstallData(request, response);
				break;
			case "/searchBooks":
				this.searchBooks(request, response);
				break;
			case "/bookDetails":
				this.showBookDetails(request, response);
				break;
			case "/customerRegister":
				this.customerRegister(request, response);
				break;
			case "/processCustomerRegister":
				this.processCustomerRegister(request, response);
				break;
			case "/login":
				this.getLoginPage(request, response);
				break;
			case "/processLogin":
				this.processLogin(request, response);
				break;
			case "/customerAccount":
				this.customerAccount(request, response);
				break;
			case "/purchase":
				this.purchase(request, response);
				break;
			case "/makeTransaction":
				this.makeTransaction(request, response);
				break;
			case "/logout":
				this.logout(request, response);
				break;
			default:
				this.getAllBooks(request, response);
				break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	// For inserting data in the tables
	private void installData(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		this.installation.insertDataInTables();
		response.sendRedirect("./");
	}
	
	
	// For deleting data from the tables
	private void uninstallData(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		this.uninstallation.deleteDataFromTables();
		if(this.session != null) {
			this.logout(request, response);
		} else {
			response.sendRedirect("./");
		}
	}

	
	// For displaying books based on book name, author name, publisher house on home page
	private void searchBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String searchedItem = request.getParameter("searchedItem");
		List<Book> listOfBooks = this.bookDao.searchBooks(searchedItem);
		request.setAttribute("listOfBooks", listOfBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// For displaying all the details of a book along with the seller
	private void showBookDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = this.bookDao.getBookDetails(bookId);
		int sellerId = book.getSellerId();
		BookSeller seller = this.bookSellerDao.getSellerDetails(sellerId);
		request.setAttribute("book", book);
		request.setAttribute("seller", seller);
		RequestDispatcher dispatcher = request.getRequestDispatcher("bookDetails.jsp");
		dispatcher.forward(request, response);
	}

	
	// For displaying customer registration page
	private void customerRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("customerRegistration.jsp");
		dispatcher.forward(request, response);
	}
	

	// For processing registration of customers
	private void processCustomerRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		Customer customer = new Customer();
		customer.setName(request.getParameter("name"));
		customer.setEmail(request.getParameter("email"));
		customer.setPhone(request.getParameter("phone"));
		customer.setAddress(request.getParameter("address"));
		customer.setPassword(request.getParameter("password1"));
		
		String error = null;
		boolean isRegister = this.customerDao.registerCustomer(customer);
		if(!isRegister) {
			error = "error";
		}
		request.setAttribute("error", error);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registerMessage.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// For displaying user login page
	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	
	// For validating the user login credentials
	private void processLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		Customer customer = this.customerDao.loginCheck(email, password);
		
		if(customer == null) {
			String error = "Invalid Email/Password";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			this.session = request.getSession();
			this.session.setAttribute("customer", customer);
			response.sendRedirect("./");
		}
	}
	
	
	// For displaying the profile and orders of customers
	private void customerAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		this.session = request.getSession();
		Customer customer = (Customer) this.session.getAttribute("customer");
		int customerId = customer.getCustomerId();
		List<BookOrder> listOfOrders = new ArrayList<BookOrder>();
		listOfOrders = this.bookOrderDao.getOrderDetails(customerId);
		request.setAttribute("listOfOrders", listOfOrders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("customerAccount.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// For going to purchase page for buying a book
	private void purchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = this.bookDao.getBookDetails(bookId);
		request.setAttribute("book", book);
		RequestDispatcher dispatcher = request.getRequestDispatcher("purchase.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// For completing the transaction of purchase of a book
	private void makeTransaction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		Book book = this.bookDao.getBookDetails(bookId);
		int sellerId = book.getSellerId();
		boolean isOrdered = this.bookOrderDao.placeOrder(bookId, customerId, sellerId);
		String message = null;
		if(isOrdered) {
			boolean isStockUpdated = this.bookDao.updateStock(bookId);
			if(isStockUpdated) {
				message = "Your Order Placed Succesfully";
			} else {
				message = "Your Order did not Complete";
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("orderMessage.jsp");
		dispatcher.forward(request, response);
	}
	
	
	// For logging out the user
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.session.removeAttribute("customer");
		session.invalidate();
		response.sendRedirect("./");
	}
	

	// For displaying home page with all the books
	private void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Book> listOfBooks = this.bookDao.getAllBooks();
		request.setAttribute("listOfBooks", listOfBooks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package com.onlineBookStore.dataInstallation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class DataInstallation {
	
	DatabaseConnection dbConnection = null;
	Connection connection = null;
	String[] bookQueries = null;
	String[] customerQueries = null;
	String[] categoryQeuries = null;
	String[] sellerQueries = null;
	
	
	public DataInstallation(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
		setQueries();
	}
	
	
	public void setQueries() {
		
		String bookQueries[] = new String[] {
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Hillbilly Elegy: A Memoir of a Family and Culture in Crisis\", \"Walt Whitman\", \"Downtown Publisher House\", 2016, 3, 720, 540, 1, 40, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Basic Engineering Mathematics\", \"Norman Cousins\", \"McGraw Hill\", 2013, 1, 377, 360, 1, 20, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Three Mistakes of My Life\", \"Chetan Bhagat\", \"Rupa Publications\", 2008, 7, 144, 150, 1, 80, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Art of Drawing the Human Body\", \"Andrew Garden\", \"Himalaya Publishers\", 2007, 2, 162, 190, 2, 30, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Web Design with HTML and CSS\", \"Richard Brenson\", \"O' Rielly\", 2011, 14, 307, 280, 2, 50, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"How to Write a Business Plan\", \"MS Robin\", \"Pearson\", 2010, 4, 290, 250, 2, 40, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"The Immortal Life of Henrietta Lacks\", \"Rebecca Skloot\", \"Crown\", 2010, 5, 283, 300, 3, 100, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"The Art and Craft of Problem Solving\", \"Paul zeitz\", \"John Wiley & Sons,Inc\", 2009, 5, 383, 350, 3, 95, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Jane Eyre\", \"Charlotte Bronte\", \"Smith,Elder and Co\",1847 ,6 , 242, 456, 3, 200, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"The Great Gatsby\", \"F. Scott Fitzgerald\", \"Charles Scribner's Sons\", 1945, 7, 400, 850, 4, 70, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Pride and Prejudice\", \"Jane Austen\", \"Whitehall\", 1813, 7, 356, 786, 4, 60, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Little Women\", \"Louisa May Alcott\", \"Roberts Brothers\", 1968, 7, 112, 1500, 4, 90, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Animal Farm\", \"George Orwell\", \"Secker and Warburg\", 1945 ,11, 864, 657, 5, 78, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Anna Karenina\", \"Leo Tolstoy\", \"The Russian Messenger\", 1878, 5, 450, 890, 5, 89, 0)",
			"INSERT INTO `book` (`bookName`, `authorName`, `publisherHouse`, `yearOfPublishing`, `categoryId`, `numOfPages`, `price`, `sellerId`, `stock`, `numOfPurchases`) VALUES (\"Alice's Adventures in Wonderland\", \"Lewis Carroll\", \"Macmillian\", 1865, 5, 800, 1000, 5, 150, 0)"
		};
		this.bookQueries = bookQueries;
		
		String categoryQeuries[] = new String[] {
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (1, 'Academic & Education')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (2, 'Art')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (3, 'Biography')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (4, 'Business & Career')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (5, 'Children & Youth')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (6, 'Environment')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (7, 'Fiction & Literature')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (8, 'Health & Fitness')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (9, 'Lifestyle')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (10, 'Personal Growth')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (11, 'Politics & Law')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (12, 'Religion')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (13, 'Science & Research')",
			"INSERT INTO `category` (`categoryId`, `categoryName`) VALUES (14, 'Technology')"
		};
		this.categoryQeuries = categoryQeuries;
		
		String customerQueries[] = new String[] {
			"INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (\"Al Pacino\", \"pacino890@gmail.com\", \"7889564512\", \"U G Deluxe Lodge Complex, 3rd Cross Thulasi Thota Road, J M Lane, Balepet Bangalore Karnataka 560053\", \"Pacino#565\")",
			"INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (\"Marlon Brando\", \"marlon456@gmail.com\", \"8956345745\", \"1281 , th Cross nd Stage, Indiranagar BangaloreKarnataka 560038\", \"Brando#343\")",
			"INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (\"Marilyn Monroe\", \"marilyn56@gmail.com\", \"9865451354\", \"48 , Mahaveer Complex, Kids Kemp Building, K G Road Bangalore Karnataka 560009\", \"Marilyn#238\")",
			"INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (\"Brad Pitt\", \"pitt2020@gmail.com\", \"6874551323\", \"No-131lbghrdblr-27, Wilson Garden Bangalore Karnataka 560027\", \"Brad#323\")",
			"INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (\"Tom Cruise\", \"cruise322@gmail.com\", \"9844554123\", \"240 , Godaji Chawl, Kalbadevi, Chira Bazar Bangalore Karnataka 560002\", \"Cruise#896\")"
		};
		this.customerQueries = customerQueries;
		
		String sellerQueries[] = new String[] {
			"INSERT INTO `bookSeller` (`ownerName`, `ownerEmail`, `ownerPhone`, `bookShopName`, `address`, `aadharNumber`, `panNumber`, `password`) VALUES(\"Buddhadev Manohari\", \"buddhadev56@gmail.com\", \"9853454548\", \"Manohori Enterprise\", \"607, 12th Main Rd, 7th Cross, HAL 2nd Stage, Indiranagar, Bengaluru, Karnataka 560038\", \"457865321478\", \"KKNPS4545F\", \"manohari@5665\")",
			"INSERT INTO `bookSeller` (`ownerName`, `ownerEmail`, `ownerPhone`, `bookShopName`, `address`, `aadharNumber`, `panNumber`, `password`) VALUES(\"Suvrata Raghunathan\", \"suvrata787@gmail.com\", \"8653245665\", \"Suvarta Books\", \"4th Floor 26/1 Dr. Rajkumar Road Malleswaram, Rajajinagar, Bengaluru, Karnataka 560055\", \"895623455689\", \"JDHDJ8685D\", \"raghunathan@56\")",
			"INSERT INTO `bookSeller` (`ownerName`, `ownerEmail`, `ownerPhone`, `bookShopName`, `address`, `aadharNumber`, `panNumber`, `password`) VALUES(\"Snehanshn Tyagri\", \"snehanshn34@gmail.com\", \"9854653255\", \"Tyagri Books\", \"38, 1st Floor, JB Nagar Main Rd, opposite Indian Oil pump, LIC Colony, HAL 3rd Stage, Sector 11, Jeevan Bima Nagar, Bengaluru, Karnataka 560075\", \"758965412325\", \"DHDKD6939H\", \"tyagri@564\");",
			"INSERT INTO `bookSeller` (`ownerName`, `ownerEmail`, `ownerPhone`, `bookShopName`, `address`, `aadharNumber`, `panNumber`, `password`) VALUES(\"Akshath Shreerang\", \"akshath343@gmail.com\", \"7568315686\", \"Akshath Books\", \"108, Outer Ring Rd, near Devegowda Petrol Bunk, Kathreguppe, Banashankari 3rd Stage, Banashankari, Bengaluru, Karnataka 560085\", \"745214896325\", \"SJSKS3434F\", \"shreerang@343\")",
			"INSERT INTO `bookSeller` (`ownerName`, `ownerEmail`, `ownerPhone`, `bookShopName`, `address`, `aadharNumber`, `panNumber`, `password`) VALUES(\"Jignesh Vimal Sabeena\", \"jignesh94@gmail.com\", \"6985326596\", \"Jignesh Books\", \"No.1101,1st Floor,Opp Food World ,Above ICICI Bank, Nagar, 24th Main Rd, 1st Phase, JP, Bengaluru, Karnataka 590078\", \"451278456398\", \"DHDJD1213S\", \"sabeena@134\")"
		};
		this.sellerQueries = sellerQueries;
	}
	

	public void insertDataInTables() throws SQLException {
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		Statement stmt = this.connection.createStatement();
		for(int i=0; i<15; i++) {
			stmt.executeUpdate(this.bookQueries[i]);
		}
		System.out.println("Book Data Inserted");
		
		for(int i=0; i<14; i++) {
			stmt.executeUpdate(this.categoryQeuries[i]);
		}
		System.out.println("Category Data Inserted");
		
		for(int i=0; i<5; i++) {
			stmt.executeUpdate(this.customerQueries[i]);
		}
		System.out.println("Customer Data Inserted");

		for(int i=0; i<5; i++) {
			stmt.executeUpdate(this.sellerQueries[i]);
		}
		System.out.println("Book Seller Data Inserted");
		
		this.dbConnection.disconnect();
	}

}

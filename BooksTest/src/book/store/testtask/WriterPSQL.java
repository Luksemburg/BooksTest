package book.store.testtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;

public class WriterPSQL implements WriterDB {
	
	private final static String url = "jdbc:postgresql://localhost:5432/books?serverTimezone=Europe/Moscow&useSSL=false";
	private final static String user = "postgres";
	private final static String pass = "123456";	
	
	private final static Logger log = Logger.getLogger(WriterPSQL.class.getName());

	@Override
	public void writeOrder(String firstName, String lastName, String address, String quantity, String bookId) throws Exception {
		//log.info("First: " + firstName);
		//log.info("Second: " + lastName);
		//log.info("Address: " + address);
		//log.info("Quant: " + quantity);
		//log.info("ID: " + bookId);
		
		
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			

			Random r = new Random();
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO orders (order_id, book_to_order_id, quantity, address, first_name, last_name) VALUES ("  + 
										r.nextInt(Integer.MAX_VALUE) + " , " + Integer.parseInt(bookId) +  " , " + Integer.parseInt(quantity)  
										+ " , '" + address + "' , '" + firstName + "' , '" + lastName + "')");		
			
			log.info("Creating of order success! " + n + " string was added!");
			
			
			st.close();
			connect.close();
		
	}
	
	@Override
	public void	writeBook(String title, String price, String description, String genres, String authors) throws Exception {
		log.info("1: " + title);
		log.info("2: " + price);
		log.info("3: " + description);
		log.info("4: " + genres);
		log.info("5: " + authors);
		
		
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
				

			String[] auth = authors.split(",");
			String[] genr = genres.split(",");
			
			Random r = new Random();
			Integer id = r.nextInt(Integer.MAX_VALUE);
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO books (book_id, title, description, price) VALUES (" + 
										id + " , '" + title + "' , '" + description 
										+ "' , " + Integer.parseInt(price) + ")");
			
			//authors
			for(int i = 0; i < auth.length; i++) {				
				auth[i] = auth[i].trim();
				
				if(!authorCheck(auth[i])) {
					authorAdd(auth[i]);
				}
				
				bookAuthorAdd(id, getAuthorId(auth[i]));				
			}
			
			//genres
			for(int i = 0; i < genr.length; i++) {				
				genr[i] = genr[i].trim();
				
				if(!genreCheck(genr[i])) {
					genreAdd(genr[i]);
				}
				
				bookGenreAdd(id, getGenreId(genr[i]));				
			}						
						
			log.info("Creating of book success! " + n + " string was added!");			
			
			st.close();
			connect.close();
	}
	
	@Override
	public boolean authorCheck(String author) throws Exception{
		boolean res = false;
		
		
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			
		
		
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT author_id FROM authors WHERE author = '" + author + "'");
			
			if(!rs.next()) {
				res = false;
			}else {
				res = true;
			}
			
			rs.close();
			st.close();
			connect.close();		
						
		return res;		
	}
	
	@Override
	public void authorAdd(String author) throws Exception{
		
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();			
		
			Random r = new Random();			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO authors (author_id, author) VALUES (" + 
											r.nextInt(Integer.MAX_VALUE) + " , '" + author + "')");
			
			log.info("Creating of author successful! " + n + " string was added!");
			
			st.close();
			connect.close();	
		
	}
	
	@Override
	public void bookAuthorAdd(Integer bookId, Integer authorId) throws Exception{
	
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();			
				
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO book_author (book_id, author_id) VALUES (" + 
											bookId + " , " + authorId + ")");
			
			log.info("Creating of new relation in book_author successful! " + n + " string was added!");
			
			st.close();
			connect.close();				
	}
	
	@Override
	public Integer getAuthorId(String author) throws Exception{
		Integer res = -1;

			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT author_id FROM authors WHERE author = '" + author + "'");
			
			if(rs.next()) {
				res = rs.getInt(1);
			}
			
			rs.close();
			st.close();
			connect.close();
		
		return res;		
	}
	
	@Override
	public boolean genreCheck(String genre) throws Exception {
		boolean res = false;
		
		
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT genre_id FROM genres WHERE genre = '" + genre + "'");
			
			if(!rs.next()) {
				res = false;
			}else {
				res = true;
			}
			
			rs.close();
			st.close();
			connect.close();
		
		return res;		
	}
	
	@Override
	public Integer getGenreId(String genre) throws Exception {
		Integer res = -1;		

			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT genre_id FROM genres WHERE genre = '" + genre + "'");
			
			if(rs.next()) {
				res = rs.getInt(1);
			}
			
			rs.close();
			st.close();
			connect.close();
		
		return res;		
	}
	
	@Override
	public void genreAdd(String genre) throws Exception {
	
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();

			Random r = new Random();			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO genres (genre_id, genre) VALUES (" + 
											r.nextInt(Integer.MAX_VALUE) + " , '" + genre + "')");
			
			log.info("Creating of genre successful! " + n + " string was added!");
			
			st.close();
			connect.close();

	}
	
	@Override
	public void bookGenreAdd(Integer bookId, Integer genreId) throws Exception {

			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			int n = st.executeUpdate("INSERT INTO book_genre (book_id, genre_id) VALUES (" + 
											bookId + " , " + genreId + ")");
			
			log.info("Creating of new relation in book_genre successful! " + n + " string was added!");
			
			st.close();
			connect.close();
		
	}

	@Override
	public void deleteBook(String bookId) throws Exception {
		Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		
		Connection connect = DriverManager.getConnection(url, user, pass);
		Statement st = connect.createStatement();
		
		int n = st.executeUpdate("DELETE FROM books WHERE book_id = '" + 
				bookId + "'");
		int a = st.executeUpdate("DELETE FROM book_author WHERE book_id = '" + 
				bookId + "'");
		int g = st.executeUpdate("DELETE FROM book_genre WHERE book_id = '" + 
				bookId + "'");

		log.info("" + n + " book deleted successfuly!");
		log.info("" + a + " author relations deleted successfuly!");
		log.info("" + g + " genre relations deleted successfuly!");
		
		st.close();
		connect.close();
	}	

}

package book.store.testtask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReaderPSQL implements ReaderDB {
	
	private final static String url = "jdbc:postgresql://localhost:5432/books?serverTimezone=Europe/Moscow&useSSL=false";
	private final static String user = "postgres";
	private final static String pass = "123456";
	
	private final static Logger log = Logger.getLogger(ReaderPSQL.class.getName());
	
	@Override
	public List<String> getAuthors() {
		
		List<String> res = new ArrayList<String>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT author FROM authors");
			
			while(rs.next()) {				
				res.add(rs.getString(1));				
			}
						
			rs.close();
			st.close();
			connect.close();		
								
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		return res;
		
	}
	
	@Override
	public List<String> getGenres() {
		
		List<String> res = new ArrayList<String>();		
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery("SELECT genre FROM genres");
			
			while(rs.next()) {				
				res.add(rs.getString(1));				
			}			
			
			rs.close();
			st.close();
			connect.close();								
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return res;		
	}
	
	
	@Override
	public List<BookDTO> getBooksWithFilter(String genre, String author) {
		
		//log.info("^^^^^^ getBooksWithFilter ^^^^^^");
		//log.info("GENRE :::::: " + genre);
		//log.info("AUTHOR :::::: " + author);
		
		List<BookDTO> res = new ArrayList<BookDTO>();
		
		//String queryGenre = "";
		//String queryAuthor = "";
		
		ArrayList<Integer> booksByGenre = new ArrayList<Integer>();
		ArrayList<Integer> booksByAuthor = new ArrayList<Integer>();
		//ArrayList<Integer> booksToRender = new ArrayList<Integer>();
		
		int genreId = -1;
		int authorId = -1;
		
		if(genre == null) {
			genre = "All";
		}
		
		if(author == null) {
			author = "All";
		}
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
			//log.info("------ FOR NAME ------");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {			
			
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			/*ResultSet rs;
			ResultSet rs2;
			ResultSet rs3;
			ResultSet rs4;*/
			//log.info("------ CONNECTION ------");
			
			if(!genre.equals("All")) {
				//log.info("++++ ++++ ++++");
				
				ResultSet rs = st.executeQuery("SELECT genre_id FROM genres WHERE genre ='" + genre + "'");	
				log.info("RS == " + rs);
				if(rs.next()) {
					genreId = rs.getInt(1);
					//log.info("====" + rs.getInt(1));
				}				
				rs.close();
				
				ResultSet rs2 = st.executeQuery("SELECT book_id FROM book_genre WHERE genre_id = '" + genreId + "'");		
				while(rs2.next()) {
					booksByGenre.add(rs2.getInt(1));
				}				
				rs2.close();
				
			}else {
				ResultSet rs = st.executeQuery("SELECT DISTINCT book_id FROM book_genre");		
				//log.info("==== ==== ====");
				
				while(rs.next()) {
					booksByGenre.add(rs.getInt(1));			
					//log.info("====" + rs.getInt(1));
				}
				rs.close();
			}					
			
			
			if(!author.equals("All")) {
				ResultSet rs3 = st.executeQuery("SELECT author_id FROM authors WHERE author ='" + author + "'");			
				if(rs3.next()) {
					authorId = rs3.getInt(1);
				}
				rs3.close();				
				
				ResultSet rs4 = st.executeQuery("SELECT book_id FROM book_author WHERE author_id = '" + authorId + "'");		
				while(rs4.next()) {
					booksByAuthor.add(rs4.getInt(1));
				}				
				rs4.close();
				
			}else {
				ResultSet rs3 = st.executeQuery("SELECT DISTINCT book_id FROM book_author");		
				while(rs3.next()) {
					booksByAuthor.add(rs3.getInt(1));
				}
				rs3.close();
			}
			
			
			for(int i = 0; i < booksByGenre.size(); i++) {
				if(booksByAuthor.contains(booksByGenre.get(i))) {
					//booksToRender.add(booksByGenre.get(i));
					
					BookDTO book = new BookDTO();
					book.setId(booksByGenre.get(i));
					book.setTitle(getBookItem("title", booksByGenre.get(i)));
					book.setDescription(getBookItem("description", booksByGenre.get(i)));
					book.setPrice(Integer.parseInt(getBookItem("price", booksByGenre.get(i))));
					
					book.setGenres(getGenresById(booksByGenre.get(i)));
					book.setAuthors(getAuthorsById(booksByGenre.get(i)));
					
					res.add(book);	
				}
			}
			
			
			
			
			/*
			while(rs.next()) {		
				BookDTO book = new BookDTO();
				
				//res.add(rs.getString(1));
				res.add(book);	
			}	*/		
			
			//rs.close();
			//rs3.close();
			st.close();
			connect.close();								
			
		}catch(Exception e) {
			e.printStackTrace();
			//log.info("====== FALSE ======");
		}
		
		return res;	
	}

	
	
	@Override
	public String getBookItem(String item, int id) {
		String res = "";
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs;
			
			rs = st.executeQuery("SELECT " + item + " FROM books WHERE book_id = '" + id + "'");	
			if(rs.next()) {
				res = rs.getString(1);
			}
			
			rs.close();
			st.close();
			connect.close();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		
		return res;
	}
	
	
	@Override
	public List<String> getGenresById(Integer id) {
		
		List<String> res = new ArrayList<String>();	
		ArrayList<Integer> genreIds = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs;
			
			rs = st.executeQuery("SELECT genre_id FROM book_genre WHERE book_id = '" + id + "'");	
			while(rs.next()) {
				genreIds.add(rs.getInt(1));
			}
			
			for(int i = 0; i < genreIds.size(); i++) {
				rs = st.executeQuery("SELECT genre FROM genres WHERE genre_id = '" + genreIds.get(i) + "'");
				if(rs.next()) {
					res.add(rs.getString(1));
				}
			}
			
			rs.close();
			st.close();
			connect.close();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}
		
		return res;
	}
	
	@Override
	public List<String> getAuthorsById(Integer id) {
		
		List<String> res = new ArrayList<String>();	
		ArrayList<Integer> authorIds = new ArrayList<Integer>();
		
		try {
			Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Connection connect = DriverManager.getConnection(url, user, pass);
			Statement st = connect.createStatement();
			ResultSet rs;
					
			rs = st.executeQuery("SELECT author_id FROM book_author WHERE book_id = '" + id + "'");	
			while(rs.next()) {
				authorIds.add(rs.getInt(1));
			}		
			
			for(int i = 0; i < authorIds.size(); i++) {
				rs = st.executeQuery("SELECT author FROM authors WHERE author_id = '" + authorIds.get(i) + "'");
				if(rs.next()) {
					res.add(rs.getString(1));
				}
			}
			
			rs.close();
			st.close();
			connect.close();
			
		}catch(Exception e) {
			e.printStackTrace();			
		}	
		
		
		return res;
	}
	
}

package book.store.testtask;

public interface WriterDB {
	void writeOrder(String firstName, String lastName, String Address, String quantity, String bookId) throws Exception;
	void writeBook(String title, String price, String description, String genres, String authors) throws Exception;
	boolean authorCheck(String author) throws Exception;
	boolean genreCheck(String genre) throws Exception;
	void authorAdd(String author) throws Exception;	
	Integer getAuthorId(String author) throws Exception;
	void bookAuthorAdd(Integer bookId, Integer authorId) throws Exception;
	Integer getGenreId(String genre) throws Exception;
	void genreAdd(String genre) throws Exception;
	void bookGenreAdd(Integer bookId, Integer genreId) throws Exception;
	void deleteBook(String bookId)  throws Exception;
}

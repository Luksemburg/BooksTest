package book.store.testtask;

import java.util.List;

public interface ReaderDB {

	List<String> getAuthors();
	List<String> getGenres();
	List<BookDTO> getBooksWithFilter(String genre, String author);
	String getBookItem(String item, int id);
	List<String> getGenresById(Integer id);
	List<String> getAuthorsById(Integer id);

}

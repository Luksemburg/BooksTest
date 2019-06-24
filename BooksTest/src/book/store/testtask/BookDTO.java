package book.store.testtask;

import java.util.List;

public class BookDTO implements Book {
	private String title;
	private String description;
	private List<String> authors;
	private List<String> genres;
	private int price;
	private int id;
	
	public void setId(Integer id) {		
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setGenres(List<String> genres) {		
		this.genres = genres;
	}
	
	public void setAuthors(List<String> authors) {		
		this.authors = authors;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	
	public String getDescriptione() {
		return description;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getId() {
		return id;
	}
	
	public List<String> getGenres() {
		return genres;
	}
	
	public List<String> getAuthors() {
		return authors;
	}
}

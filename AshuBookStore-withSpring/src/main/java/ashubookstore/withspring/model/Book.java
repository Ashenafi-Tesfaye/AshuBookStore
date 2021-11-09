package ashubookstore.withspring.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="books")
public class Book {
	
	@Id
	@Column(name="book_id", nullable = false, unique = true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(nullable = true, updatable=true, length = 9)
	private String isbn;
	@Column(nullable = true, updatable=true, length = 255)
	private String authors;
	@Column(nullable = true, updatable=true)
	private Integer pubYear;
	@Column(nullable = false, updatable=true, length = 255)
	private String title;
	@Column(nullable = true, updatable=true)
	private Double averageRating;
	@Column(nullable = true, updatable=true)
	private Integer ratings;
	@Column(nullable = false, updatable=true)
	private BigDecimal price;
	@Column(nullable = false, updatable=true, length = 255)
	private String image;
	@Column(nullable = false, updatable=true, length = 50)
	private Long stock;
	
	public Book() {
		
	}

	public Book(String isbn, String authors, Integer pubYear, String title, Double averageRating, Integer ratings,
			BigDecimal price, String image, Long stock) {
		
		super();
		this.isbn = isbn;
		this.authors = authors;
		this.pubYear = pubYear;
		this.title = title;
		this.averageRating = averageRating;
		this.ratings = ratings;
		this.price = price;
		this.image = image;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public Integer getPubYear() {
		return pubYear;
	}

	public void setPubYear(Integer pubYear) {
		this.pubYear = pubYear;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getRatings() {
		return ratings;
	}

	public void setRatings(Integer ratings) {
		this.ratings = ratings;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authors, averageRating, id, image, isbn, price, pubYear, ratings, stock, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Book other = (Book) obj;
		return Objects.equals(authors, other.authors) && Objects.equals(averageRating, other.averageRating)
				&& id == other.id && Objects.equals(image, other.image) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(price, other.price) && Objects.equals(pubYear, other.pubYear)
				&& Objects.equals(ratings, other.ratings) && Objects.equals(stock, other.stock)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", authors=" + authors + ", pubYear=" + pubYear + ", title="
				+ title + ", averageRating=" + averageRating + ", ratings=" + ratings + ", price=" + price + ", image="
				+ image + ", stock=" + stock + "]";
	}
	
		
	
}

package ashubookstore.withspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ashubookstore.withspring.model.Book;
import ashubookstore.withspring.service.BookService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping("/book")
	public String getBookDetails (@RequestParam String id, @RequestParam String title, ModelMap model) {
		model.put("title", "OnlineBookStore |" + title);
		Book book = bookService.getBookById(id);
		model.put("book", book);
		model.put("similarBooks", bookService.getBooksByAuthor(book));
		return "book-details";
	}
	
}

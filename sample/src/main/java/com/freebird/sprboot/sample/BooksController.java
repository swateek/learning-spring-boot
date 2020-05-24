package com.freebird.sprboot.sample;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	BookMockedData mockData = BookMockedData.getInstance();
	
	@GetMapping("/books")
    public List<Book> index() {
		return mockData.fetchBooks();
    }
	
	@GetMapping("/books/{id}")
    public Book show(@PathVariable String id) {
		int bookId = Integer.parseInt(id);
        return mockData.getBookById(bookId);
    }
	
	@PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return mockData.searchBook(searchTerm);
    }
	
	@PostMapping("/books")
    public Book create(@RequestBody Map<String, String> body){
        int id = Integer.parseInt(body.get("id"));
        String title = body.get("title");
        String author = body.get("author");
        return mockData.createBook(id, title, author);
    }
	
	@PutMapping("/books/{id}")
    public Book update(@PathVariable String id, @RequestBody Map<String, String> body){
        int bookId = Integer.parseInt(id);
        String title = body.get("title");
        String author = body.get("author");
        return mockData.updateBook(bookId, title, author);
    }
	
	@DeleteMapping("books/{id}")
    public boolean delete(@PathVariable String id){
        int bookId = Integer.parseInt(id);
        return mockData.delete(bookId);
    }
}

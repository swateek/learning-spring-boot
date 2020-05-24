package com.freebird.sprboot.sample.unit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.freebird.sprboot.sample.Book;
import com.freebird.sprboot.sample.BookMockedData;
import com.freebird.sprboot.sample.BooksController;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	BookMockedData mockData = BookMockedData.getInstance();
	   
	@MockBean
	private BooksController booksController;
	
	@Test
	public void createBook() throws Exception {
		int id = 3000;
        String title = "The Mahabharat";
        String author = "Vedavyasa";
        Book expectedResult = new Book(id, title, author);
        
        Map<String, String> reqBody = new HashMap<>();
        reqBody.put("id", String.valueOf(id));
        reqBody.put("title", title);
        reqBody.put("author", author);
		
		given(booksController.create(reqBody)).willReturn(expectedResult);
		
		mvc.perform(post("/books/")
	               .contentType(APPLICATION_JSON)
	               .characterEncoding("utf-8")
	               .content((new JSONObject(reqBody)).toString()))
				   .andDo(MockMvcResultHandlers.print())
	               .andExpect(status().isOk())
	               .andExpect(content().contentType("application/json"))
	               .andExpect(jsonPath("$.author").value(expectedResult.getAuthor()));
	}
	
	@Test
	public void fetchAllBooks() throws Exception {
		List<Book> expectedResult = mockData.fetchBooks();
		
		given(booksController.index()).willReturn(expectedResult);
		
		mvc.perform(get("/books")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk());
	}
	
	@Test
	public void fetchBooksById() throws Exception {
		int bookId = 3;
		Book expectedResult = mockData.getBookById(bookId);
		
		given(booksController.show(String.valueOf(bookId))).willReturn(expectedResult);
		
		mvc.perform(get("/books/"+bookId)
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("author", is(expectedResult.getAuthor())));
	}
	
	@Test
	public void searchBooks() throws Exception {
		Map<String, String> reqBody = new HashMap<>();
        reqBody.put("test", "clear");
        
        List<Book> expectedResult = mockData.searchBook("clear");
        
        given(booksController.search(reqBody)).willReturn(expectedResult);
        
        mvc.perform(get("/books")
	               .contentType(APPLICATION_JSON))
	               .andExpect(status().isOk());
	}
	
}

package com.geek.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.geek.entity.Authour;
import com.geek.service.BookService;

@WebMvcTest(BookApi.class)
public class BookApiTest {

	//if /books/authour
	    //--pass json data
	@MockBean
	private BookService bookService;
	 @Autowired
	private MockMvc mockMvc;
	 Authour authour=new Authour();
	 {
		 authour.setAuthourId(101);
		 authour.setName("Test Authour");
	 }
	 @Test
	 public void testAddAuthour() throws Exception {
		// Authour authour=new Authour();
		// authour.setAuthourId(101);
		// authour.setName("Test Authour");
		//when(bookService.addAuthour(authour)).thenReturn(authour);
		  when(bookService.addAuthour(Mockito.any(Authour.class))).thenReturn(testAuthour);
		mockMvc.perform(post("/books/authour")
				 .contentType(MediaType.APPLICATION_JSON)
				// .content("{\"authourId\":101,\"name\":\"Test Authour\"}")
				  .content("{\"authourId\": 1, \"name\": \"John Doe\"}")
				)
		  .andExpect(status().isCreated())
		    .andExpect(jsonPath("$.authourId").value(1));//return json type .....
	 }
	 private final Authour testAuthour = new Authour(1, "John Doe");
	   // private final Book testBook = new Book(1, "Test Book", Genre.COMEDY,1000,50, testAuthour);
	// @Test
	    public void testAddAuthour2() throws Exception {
		 Authour testAuthour = new Authour(1, "John Doe");
	        // Mock the service call
	        when(bookService.addAuthour(testAuthour)).thenReturn(testAuthour);
	        mockMvc.perform(post("/books/authour")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"authourId\": 1, \"name\": \"John Doe\"}"))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.name").value("John Doe"))
	                .andExpect(jsonPath("$.authourId").value(1));
	    }	
}

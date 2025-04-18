package com.geek;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.geek.dto.BookDto;
import com.geek.entity.Authour;
import com.geek.entity.Book;
import com.geek.entity.Genre;
import com.geek.exception.ApplicationException;
import com.geek.repository.AuthourRepository;
import com.geek.repository.BookRepository;
import com.geek.service.BookServiceImpl;
public class BookServiceTest {

	@Mock
	private BookRepository bookRepo;
	@Mock
	private AuthourRepository authourRepo;
	@InjectMocks 
	private BookServiceImpl bookService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);//initializes the MOCK and inject them
	}
	@Test
	void addBookTest() {
	//	assertEquals("success", "success");
		BookDto dto=new BookDto(1, "TestBook", Genre.COMEDY, 1000, 50, 11);
		Authour authour= new Authour(11, "TestAuthour");//dummy authour obj .....
		when(authourRepo.findById(dto.getAuthourId())).thenReturn(Optional.of(authour));//PRE Condition
		Book b=bookService.addBook(dto);
		assertEquals(dto.getBookName(), b.getBookName());
		//compare bookid, genre, stock, price ....
		assertEquals(authour.getName(), b.getAuthour().getName());
		 //test whether bookRepo.save method is Called or Not ?????
		verify(bookRepo,times(1)).save(b); //post condition....
	}
	@Test
	void addBookTestNegative() {
		BookDto dto=new BookDto(1, "TestBook", Genre.COMEDY, 1000, 50, 1);
		when(authourRepo.findById(dto.getAuthourId())).thenReturn(Optional.empty());
		Exception e=assertThrows(ApplicationException.class, ()->bookService.addBook(dto));
		assertEquals(e.getMessage(), "Authour 1 Not Exists");
		 verify(bookRepo,times(0)).save(any(Book.class));
	}
}

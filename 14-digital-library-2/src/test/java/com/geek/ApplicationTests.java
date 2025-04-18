package com.geek;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.geek.entity.Book;
import com.geek.entity.Genre;
import com.geek.entity.Penalty;
import com.geek.entity.Transaction;
import com.geek.repository.PenaltyRepository;
import com.geek.repository.TransactionRepository;
import com.geek.service.UserServiceImpl;


//@SpringBootTest
class ApplicationTests {
	@Mock
	TransactionRepository transRepo;
	@Mock
	PenaltyRepository penaltyRepo;
	@InjectMocks
	UserServiceImpl userService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);//initializes the MOCK and inject them
	}
	@Test
	void testReturnBook() {
	Transaction t=new Transaction();
	t.setTransactionId(1);
	t.setBorrowDate(LocalDate.of(2024, 9, 10));
	Book book=new Book(101, "TestBook1", Genre.COMEDY, 1000.0f, 40, null);
	t.setBook(book);
	when(transRepo.findById(1)).thenReturn(Optional.of(t));
	Book returnedBook=userService.returnBook(1);
	assertEquals(book.getStock(), returnedBook.getStock());
	Penalty p=t.getPenalty();
	assertEquals(150, p.getAmount());
	assertEquals("FINE", p.getRemarks());
	assertEquals("RETURNED", t.getStatus());
	//POST condition
	//do the verify test ....
	}
	
}

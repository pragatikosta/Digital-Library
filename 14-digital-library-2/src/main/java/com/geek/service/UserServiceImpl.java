package com.geek.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.geek.dto.BorrowBookDto;
import com.geek.entity.Book;
import com.geek.entity.Penalty;
import com.geek.entity.Transaction;
import com.geek.entity.User;
import com.geek.exception.ApplicationException;
import com.geek.repository.BookRepository;
import com.geek.repository.PenaltyRepository;
import com.geek.repository.TransactionRepository;
import com.geek.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserServiceImpl implements UserService {
@Autowired
	private UserRepository userRepo;
@Autowired
private BookRepository bookRepo;
@Autowired
private PenaltyRepository penaltyRepo;
@Autowired
private PasswordEncoder encoder;

	@Override
	public User addNewUser(User u) {
		String pwd=u.getPassword();
		u.setPassword(encoder.encode(pwd));
		//insert or update
		return userRepo.save(u);
	}
	@Autowired
	private TransactionRepository transRepo;
	@Override
	public Book borrowBook(BorrowBookDto borrowDto,String uname) {
		int usrId=userRepo.findByUserName(uname).getUserId();
		int bookId=borrowDto.getBookId();
		User user=
				userRepo.findById(usrId).orElseThrow(()-> new ApplicationException("invalid user id "+usrId));
		Book book=bookRepo.findById(bookId).orElseThrow(()-> new ApplicationException("invalid Book id "+bookId));
		if(checkAvailability(bookId)) {
			Transaction t=new Transaction();
			t.setBorrowDate(LocalDate.now());
			//since Enums are typesafe
			t.setStatus("BORROWED");//use ENUM not String
			t.setBook(book);
			t.setUser(user);
			book.setStock(book.getStock()-1);
			transRepo.save(t);
		}
		return book;
	}
	@Override
	public Book returnBook(int tid) {
		Transaction t=transRepo.findById(tid).orElseThrow(() -> new ApplicationException("Invalid transaction id "+tid));
		 LocalDateTime borrowedDate=t.getBorrowDate().atStartOfDay();
		 LocalDateTime curDate=LocalDateTime.now();
		 Duration duration= Duration.between(borrowedDate, curDate);
		 long daysDiff= duration.toDays();
		 if(daysDiff>30) {
			 Penalty p=new Penalty();
			 p.setNoOfDays((int)daysDiff-30);
			 float amt=(daysDiff-30)*50;
			 p.setAmount(amt);
			 p.setRemarks("FINE");
			 t.setPenalty(p); //penalty is stored in transaction
			 penaltyRepo.save(p);
		 }
		 t.setReturnedDate(LocalDate.now());;
		 t.setStatus("RETURNED");
		 Book b=t.getBook();
		b.setStock(b.getStock()+1);
		return b;
	}

	@Override
	public boolean checkAvailability(int bookId) {
		Book book=bookRepo.findById(bookId).orElseThrow(()-> new ApplicationException("invalid Book id "+bookId));
        if(book.getStock()>0)
        	return true;
		return false;
	}
	@Override
	public List<Transaction> chkTransactionByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

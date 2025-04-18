package com.geek.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.geek.dto.BookDto;
import com.geek.entity.Authour;
import com.geek.entity.Book;
import com.geek.entity.Genre;
import com.geek.entity.Transaction;

public interface BookService {
Authour addAuthour(Authour authour);
Book addBook(BookDto book);
List<Book> searchByGenre2(Genre genre);

List<Book> allBooks();
Page<Book> allBookswithPagination(int pageNo,int size);
List<Book> searchByAuthour(int authourId);
List<Book> searchByGenre(Genre genre);
Book updateBook(Book book,int bookId);
void removeBook(int bookId);

List<Book> searchByBookName(String bookName);

}

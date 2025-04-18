package com.geek.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.geek.dto.BookDto;
import com.geek.entity.Authour;
import com.geek.entity.Book;
import com.geek.entity.Genre;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
//@Repository
public class BookRepositoryImpl /* implements BookRepository */{
	/*
	 * // @PersistenceContext private EntityManager em;
	 * 
	 * @Override public Authour addAuthourRepo(Authour authour) {
	 * em.persist(authour); return authour; }
	 * 
	 * @Override public Book addBookRepo(Book book) { em.persist(book); return book;
	 * }
	 * 
	 * @Override public List<Book> searchByGenre2Repo(Genre genre) { // TODO
	 * Auto-generated method stub //initialize EM //query object //get result return
	 * null; }
	 * 
	 * @Override public List<Book> allBooksRepo() { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public List<Book> searchByAuthourRepo(int authourId) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public List<Book> searchByGenreRepo(String genre) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Book updateBookRepo(Book book, int bookId) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public void removeBookRepo(int bookId) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public List<Book> searchByBookNameRepo(String bookName) { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * @Override public Authour searchById(int id) { // TODO Auto-generated method
	 * stub return em.find(Authour.class, id); }
	 */
}

package com.geek.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geek.dto.BookDto;
import com.geek.entity.Authour;
import com.geek.entity.Book;
import com.geek.entity.Genre;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //query method
	List<Book> findByGenre(Genre genre);
	
	@Query(value = "select b from Book b where b.genre= :g")
	List<Book> searchBooksByGenre( @Param("g") Genre genre);
	
	@Query(value = "select * from Book b where b.authour_id= :a",nativeQuery = true)
	List<Book> searchBooksByAuthourId(@Param("a") int authourId);
//	Authour addAuthourRepo(Authour authour);
//	Book addBookRepo(Book book);
//	List<Book> searchByGenre2Repo(Genre genre);
//
//	List<Book> allBooksRepo();
//	List<Book> searchByAuthourRepo(int authourId);
//	List<Book> searchByGenreRepo(String genre);
//	Book updateBookRepo(Book book,int bookId);
//	void removeBookRepo(int bookId);
//	List<Book> searchByBookNameRepo(String bookName);
//	Authour searchById(int id);
}

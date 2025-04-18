package com.geek.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
//inverse side
@Entity
//@Setter
//@Getter
//@AllArgsConstructor

public class Authour {
	@Id
private int authourId;
private String name;
//one to many
@JsonIgnore
@OneToMany(mappedBy = "authour",cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
private List<Book> books;
public Authour(int authourId, String name) {
	super();
	this.authourId = authourId;
	this.name = name;
}
public Authour(){
	
}
public int getAuthourId() {
	return authourId;
}
public void setAuthourId(int authourId) {
	this.authourId = authourId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<Book> getBooks() {
	return books;
}
public void setBooks(List<Book> books) {
	this.books = books;
}

}

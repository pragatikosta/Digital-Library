package com.geek.entity;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Transaction {
	@Id
	@GeneratedValue
private int transactionId;
private LocalDate borrowDate;
private LocalDate returnedDate;
private String status;
//many to one
@ManyToOne
@JoinColumn(name="usr_id")
private User user; 
//many to one 
@ManyToOne
@JoinColumn(name="book_id")
private Book book;
//one to one
@OneToOne
@JoinColumn(name="penalty_id")
private Penalty penalty;
}

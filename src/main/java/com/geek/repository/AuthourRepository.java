package com.geek.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.geek.entity.Authour;

public interface AuthourRepository extends JpaRepository<Authour, Integer>{
//List<Authour> findAll(Pageable p);
}

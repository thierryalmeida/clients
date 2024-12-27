package com.tralmeida.clients.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tralmeida.clients.entities.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	List<Client> findByEmail(String email);
	
	List<Client> findByDtNascimentoBetween(Date dtIni, Date dtFim);
}

package com.tralmeida.clients.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tralmeida.clients.entities.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM client WHERE client.email = :email")
	List<Client> searchByEmail(String email);
	
	@Query(nativeQuery = true, value = "SELECT * FROM client WHERE dtNascimento BETWEEN :dtIni AND :dtFim")
	List<Client> searchByDtNascimentoBetween(Date dtIni, Date dtFim);
}

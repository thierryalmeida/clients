package com.tralmeida.clients.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tralmeida.clients.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
}

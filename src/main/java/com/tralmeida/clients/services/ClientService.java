package com.tralmeida.clients.services;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
		return null;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById() {
		return null;
	}
	
	@Transactional
	public ClientDTO create() {
		return null;
	}
	
	@Transactional
	public ClientDTO update() {
		return null;
	}
	
	public void delete() {
		return;
	}
}

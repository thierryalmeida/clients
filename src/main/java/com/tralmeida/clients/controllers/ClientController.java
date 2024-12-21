package com.tralmeida.clients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(){
		return null;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(){
		return null;
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<ClientDTO> update(){
		return null;
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(){
		return null;
	}
}

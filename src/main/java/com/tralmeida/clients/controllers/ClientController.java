package com.tralmeida.clients.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		return null;
	}
	
	@GetMapping(value = "/nome")
	public ResponseEntity<List<ClientDTO>> findByNome(@RequestParam(defaultValue = "") String nome){
		return null;
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<List<ClientDTO>> findByEmail(@RequestParam(defaultValue = "") String email){
		return null;
	}
	
	@GetMapping(value = "/dtNascimento")
	public ResponseEntity<List<ClientDTO>> findByDtNascimentoBetWeen(
			@RequestParam(defaultValue = "") String dtIni, 
			@RequestParam(defaultValue = "") String dtFim){
		return null;
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
		return null;
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto){
		return null;
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		return null;
	}
}

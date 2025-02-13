package com.tralmeida.clients.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
		Page<ClientDTO> page = service.findAll(pageable);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		ClientDTO result = service.findById(id);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/nome")
	public ResponseEntity<List<ClientDTO>> findByNome(@RequestParam(defaultValue = "") String nome){
		List<ClientDTO> result = service.findByNome(nome);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<List<ClientDTO>> findByEmail(@RequestParam(defaultValue = "") String email){
		List<ClientDTO> result = service.findByEmail(email);
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value = "/dtNascimento")
	public ResponseEntity<List<ClientDTO>> findByDtNascimentoBetWeen(
			@RequestParam(defaultValue = "") String dtIni, 
			@RequestParam(defaultValue = "") String dtFim){
		List<ClientDTO> result = service.findByDtNascimentoBetween(dtIni, dtFim);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
		dto = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

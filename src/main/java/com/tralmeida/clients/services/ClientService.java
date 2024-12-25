package com.tralmeida.clients.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.entities.Client;
import com.tralmeida.clients.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	//JPA
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable){
		Page<Client> page = repository.findAll(pageable);
		return page.map(x -> new ClientDTO(x));
	}
	
	//JPA
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(repository.findById(id).get());
	}
	
	//JDBC
	@Transactional(readOnly = true)
	public List<ClientDTO> findByNome(String nome) {
		return null;
	}
	
	//Native query
	@Transactional(readOnly = true)
	public ClientDTO findByEmail(String email) {
		return null;
	}
	
	//Native query
	@Transactional(readOnly = true)
	public List<ClientDTO> findByDtNascimentoBetween(Date dtIni, Date dtFim) {
		return null;
	}
	
	//JPA
	@Transactional
	public ClientDTO create(ClientDTO dto) {
		Client entity = copyDtoToEntity(dto);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}
	
	//JDBC
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		return null;
	}
	
	//JDBC
	public void delete(Long id) {
		return;
	}
	
	private Client copyDtoToEntity(ClientDTO dto) {
		return new Client(
				dto.getId(), 
				dto.getNome(), 
				dto.getEmail(), 
				dto.getTelefone(), 
				dto.getdtNascimento(), 
				dto.getdtInclusao());
	}
}

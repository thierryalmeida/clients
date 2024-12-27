package com.tralmeida.clients.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.entities.Client;
import com.tralmeida.clients.repositories.ClientJdbcRepository;
import com.tralmeida.clients.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientJdbcRepository jdbcRepository;
	
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
		List<Client> list = jdbcRepository.findByNome(nome);
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}
	
	//Native query
	@Transactional(readOnly = true)
	public List<ClientDTO> findByEmail(String email) {
		List<Client> list = repository.searchByEmail(email);
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
	}
	
	//Native query
	@Transactional(readOnly = true)
	public List<ClientDTO> findByDtNascimentoBetween(Date dtIni, Date dtFim) {
		List<Client> list = repository.searchByDtNascimentoBetween(dtIni, dtFim);
		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
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
		int updatedLines = jdbcRepository.update(id, copyDtoToEntity(dto));
		if(updatedLines == 1) {
			return new ClientDTO(repository.findById(id).get());
		} else {
			return null;
		}
	}
	
	//JDBC
	public void delete(Long id) {
		jdbcRepository.delete(id);
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

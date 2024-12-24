package com.tralmeida.clients.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tralmeida.clients.dto.ClientDTO;
import com.tralmeida.clients.entities.Client;
import com.tralmeida.clients.repositories.ClientRepository;

@ExtendWith(SpringExtension.class)
public class ClientServiceTests {

	@InjectMocks
	private ClientService service;
	
	@Mock
	private ClientRepository repository;
	
	private Long existingId;
	private Long nonExistingId;
	private Client entity;
	private PageImpl<Client> page;
	private ClientDTO clientDTO;
	
	@BeforeEach
	void setUp() {
		existingId = 1L;
		nonExistingId = 2L;
		
		entity = new Client();
		page = new PageImpl<>(List.of(entity));
		
		clientDTO = new ClientDTO();
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(entity));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(entity);
		
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
	}
	
	@Test
	public void findByIdShouldReturnClientDTOWhenIdExists() {
		ClientDTO dto = service.findById(existingId);
		
		Assertions.assertNotNull(dto);
		Mockito.verify(repository).findById(existingId);
	}
	
	@Test
	public void createShouldReturnClientDTO() {
		ClientDTO newDto = service.create(clientDTO);
		Assertions.assertNotNull(newDto);
	}
	
	@Test
	public void findAllShouldReturnPage() {
		Pageable pageable = PageRequest.of(0, 10);
		
		Page<ClientDTO> result = service.findAll(pageable);
		Assertions.assertNotNull(result);
		Mockito.verify(repository).findAll(pageable);
	}
	
	
}

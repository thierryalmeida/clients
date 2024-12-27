package com.tralmeida.clients.services;

import java.util.Date;
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
import com.tralmeida.clients.repositories.ClientJdbcRepository;
import com.tralmeida.clients.repositories.ClientRepository;

@ExtendWith(SpringExtension.class)
public class ClientServiceTests {

	@InjectMocks
	private ClientService service;
	
	@Mock
	private ClientRepository repository;
	
	@Mock
	private ClientJdbcRepository jdbcRepository;
	
	private Long existingId;
	private Long nonExistingId;
	private Client entity;
	private PageImpl<Client> page;
	private ClientDTO clientDTO;
	private String existingName;
	private int updatedLines;
	private String existingEmail;
	private Date dtIniValid;
	private Date dtFimValid;
	
	@BeforeEach
	void setUp() {
		existingId = 1L;
		nonExistingId = 2L;
		entity = new Client();
		List<Client> list = List.of(entity);
		page = new PageImpl<>(list);
		clientDTO = new ClientDTO();
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(entity));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(entity);
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		
		existingName = "Fulano Mock";
		updatedLines = 1;
		
		Mockito.when(jdbcRepository.findByNome(existingName)).thenReturn(list);
		Mockito.when(jdbcRepository.update(existingId, entity)).thenReturn(updatedLines);
		Mockito.when(jdbcRepository.delete(existingId)).thenReturn(updatedLines);
		Mockito.when(jdbcRepository.delete(nonExistingId)).thenReturn(0);
		
		existingEmail = "existing.emails@example.com";
		Mockito.when(repository.searchByEmail(existingEmail)).thenReturn(list);
		
		dtIniValid = new Date();
		dtFimValid = new Date();
		Mockito.when(repository.searchByDtNascimentoBetween(dtIniValid, dtFimValid)).thenReturn(list);
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
	
	@Test
	public void findByNomeShouldReturnListWhenNameExists() {
		List<ClientDTO> list = service.findByNome(existingName);
		Assertions.assertFalse(list.isEmpty());
		Mockito.verify(jdbcRepository).findByNome(existingName);		
	}
	
	@Test
	public void updateShouldReturnClientDTOWhenIdExists() {
		ClientDTO dto = service.update(existingId, clientDTO);
		Assertions.assertNotNull(dto);
		Mockito.verify(jdbcRepository).update(existingId, entity);
	}
	
	@Test
	public void deleteShouldReturnNothingWhenIdExists() {
		service.delete(existingId);
		Mockito.verify(jdbcRepository).delete(existingId);
	}
	
	@Test
	public void findByEmailShouldReturnListWhenEmailExists() {
		List<ClientDTO> list = service.findByEmail(existingEmail);
		Assertions.assertFalse(list.isEmpty());
		Mockito.verify(repository.searchByEmail(existingEmail));
	}
	
	@Test
	public void findByDtNascimentoBetweenValidIntervalShouldReturnList() {
		List<ClientDTO> list = service.findByDtNascimentoBetween(dtIniValid, dtFimValid);
		Assertions.assertFalse(list.isEmpty());
		Mockito.verify(repository.searchByDtNascimentoBetween(dtIniValid, dtFimValid));
	}
}

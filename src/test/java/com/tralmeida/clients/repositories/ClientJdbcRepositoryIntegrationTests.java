package com.tralmeida.clients.repositories;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tralmeida.clients.entities.Client;

@SpringBootTest
public class ClientJdbcRepositoryIntegrationTests {

	@Autowired
	private ClientJdbcRepository repository;
	
	private String existingName;
	private String existingNameForDelete;
	private Long existingIdByExistingName;
	private Long existingIdByExistingNameForDelete;
	
	private String oldName;
	private Long existingIdByOldName;
	private String nameForUpdateByOldName;
	
	
	@BeforeEach
	void setUp() {
		existingName = "Gabriela Almeida";
		existingIdByExistingName = 7L;
		
		existingNameForDelete = "Silva";
		existingIdByExistingNameForDelete = 1L;
		
		oldName = "Bruno Costa";
		nameForUpdateByOldName = "Bruno da Costa";
		existingIdByOldName = 7L;
	}
	
	@Test
	public void findByNomeShouldReturnListWhenNameExists() {
		List<Client> list = repository.findByNome(existingName);
		
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertTrue(list.get(0).getNome().contains(existingName));
		Assertions.assertEquals(list.get(0).getId(), existingIdByExistingName);
	}
	
	@Test
	public void deleteShouldDeleteClientWhenIdExists() {
		repository.delete(existingIdByExistingNameForDelete);
		
		List<Client> result = repository.findByNome(existingNameForDelete);
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void updateShouldUpdateWhenIdExists() {
		List<Client> list = repository.findByNome(oldName);
		Client client = list.get(0);
		client.setNome(nameForUpdateByOldName);;
		
		int updatedLines = repository.update(existingIdByOldName, client);
		Assertions.assertEquals(1, updatedLines);
		Assertions.assertTrue(repository.findByNome(oldName).isEmpty());
		
		List result = repository.findByNome(nameForUpdateByOldName);
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(result.size(), 1);
	}
}
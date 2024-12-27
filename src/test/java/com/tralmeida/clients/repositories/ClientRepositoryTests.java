package com.tralmeida.clients.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tralmeida.clients.entities.Client;

@DataJpaTest
public class ClientRepositoryTests {

	@Autowired
	private ClientRepository repository;
	
	private String existingEmail;
	private String invalidEmail;
	private Date dtIni70s;
	private Date dtFim70s;
	private Date dtIniInvalid;
	private Date dtFimInvalid;
	
	@BeforeEach
	void setUp() throws ParseException {
		existingEmail = "mariana.gomes@example.com";
		invalidEmail = "teste@gmail.com";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dtIni70s = sdf.parse("01/01/1970");
		dtFim70s = sdf.parse("30/12/1979");
		dtIniInvalid = new Date();
		dtFimInvalid = new Date();
	}
	
	@Test
	public void searchByEmailShouldReturnListWhenEmailExists() {
		List<Client> list = repository.searchByEmail(existingEmail);
		Assertions.assertFalse(list.isEmpty());
	}
	
	@Test
	public void searchByEmailShouldReturnEmptyListWhenEmailDoesNotExists() {
		List<Client> list = repository.searchByEmail(invalidEmail);
		Assertions.assertTrue(list.isEmpty());
	}
	
	@Test
	public void searchByDtNascimentoBetween70sIntervalShouldReturnListWhithOneClient() {
		List<Client> list = repository.searchByDtNascimentoBetween(dtIni70s, dtFim70s);
		Assertions.assertFalse(list.isEmpty());
		Assertions.assertEquals(list.size(), 1);
	}
	
	@Test
	public void searchByDtNascimentoBetweenInvalidIntervalShouldReturnEmptyList() {
		List<Client> list = repository.searchByDtNascimentoBetween(dtIniInvalid, dtFimInvalid);
		Assertions.assertTrue(list.isEmpty());
	}
}

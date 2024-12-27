package com.tralmeida.clients.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClientControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	private Long existingId;
	private Long nonExistingId;
	private Integer countTotalClients;
	
	@BeforeEach
	void setUp(){
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalClients = 50;
	}
	
	@Test
	public void findAllShouldReturnSortedPageWhenSortByNome() throws Exception{
		ResultActions result = mockMvc.perform(get("/clients?page=0&size=10&sort=nome,asc").accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalClients));
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].nome").value("Alice Gomes"));
		result.andExpect(jsonPath("$.content[1].nome").value("Ana Silva"));
	}
}

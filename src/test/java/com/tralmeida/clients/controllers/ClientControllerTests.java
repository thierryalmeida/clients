package com.tralmeida.clients.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tralmeida.clients.dto.ClientDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClientControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private ClientDTO newClient;
	private Integer countTotalClients;
	private Long nextId;
	
	@BeforeEach
	void setUp(){
		countTotalClients = 50;
		newClient = new ClientDTO(null, "Fulano", "fulano@teste.com", "40028922", new Date(), new Date());
		nextId = 51L;
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
	
	@Test
	public void createShouldReturnNewClientDTOWithAttributesAndNewId() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(newClient);
		ResultActions result = mockMvc.perform(post("/clients")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.id").value(nextId));
		result.andExpect(jsonPath("$.nome").value(newClient.getNome()));
		result.andExpect(jsonPath("$.telefone").value(newClient.getTelefone()));
	}
}

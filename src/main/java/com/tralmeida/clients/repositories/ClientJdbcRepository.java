package com.tralmeida.clients.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tralmeida.clients.entities.Client;

@Repository
public class ClientJdbcRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Client> findByNome(String nome) {
		String sql = "SELECT * FROM client WHERE UPPER(nome) like UPPER(?)";
		List<Client> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Client.class), "%"+nome+"%");
		return result;
	}
	
	public int update(Long id, Client client) {
		String sql = "UPDATE client SET nome = ?, email = ?, telefone = ?, dtnascimento = ? "
				+ "WHERE id = ?";
		return jdbcTemplate.update(sql, client.getNome(), client.getEmail(), client.getTelefone(), client.getDtNascimento(), client.getId());
	}
	
	public int delete(Long id) {
		String sql = "DELETE FROM client WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
}

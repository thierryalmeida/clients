package com.tralmeida.clients.dto;

import java.io.Serializable;
import java.util.Date;

import com.tralmeida.clients.entities.Client;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Date dtNascimento;
    private Date dtInclusao;
    
    public ClientDTO() {
    }

    public ClientDTO(Long id, String nome, String email, String telefone, Date dtNascimento, Date dtInclusao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.dtInclusao = dtInclusao;
    }
    
    public ClientDTO(Client entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
        this.dtNascimento = entity.getDtNascimento();
        this.dtInclusao = entity.getDtInclusao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getdtNascimento() {
        return dtNascimento;
    }

    public void setdtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Date getdtInclusao() {
        return dtInclusao;
    }

    public void setdtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }
}

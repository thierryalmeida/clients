package com.tralmeida.clients.dto;

import java.io.Serializable;
import java.time.Instant;

public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String nome;
    private String email;
    private String telefone;
    private Instant dtnascimento;
    private Instant dtinclusao;
    
    public ClientDTO() {
    }

    public ClientDTO(int id, String nome, String email, String telefone, Instant dtnascimento, Instant dtinclusao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtnascimento = dtnascimento;
        this.dtinclusao = dtinclusao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Instant getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Instant dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public Instant getDtinclusao() {
        return dtinclusao;
    }

    public void setDtinclusao(Instant dtinclusao) {
        this.dtinclusao = dtinclusao;
    }
}

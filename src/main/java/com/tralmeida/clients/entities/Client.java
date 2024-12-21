package com.tralmeida.clients.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    private String nome;
    private String email;
    private String telefone;
    
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtnascimento;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dtinclusao;
    
    public Client() {
    }
    
    public Client(Long id, String nome, String email, String telefone, Instant dtnascimento, Instant dtinclusao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dtnascimento = dtnascimento;
        this.dtinclusao = dtinclusao;
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dtnascimento=" + dtnascimento +
                ", dtinclusao=" + dtinclusao +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

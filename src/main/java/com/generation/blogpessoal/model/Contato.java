package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tb_contatos")
public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank private int tipoContato;
	
	@NotBlank
	private Long contato;
	
 
	@ManyToOne
	@JsonIgnoreProperties ("pessoa")
	private Pessoa pessoa;
	
    public Long getId() {
        return this.id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public int gettipoContato() {
        return this.tipoContato;
    }
 
    public void settipoContato(int tipoContato) {
        this.tipoContato = tipoContato;
    }
 
    public Long getContato() {
        return this.contato;
    }
 
    public void setContato(Long contato) {
        this.contato = contato;
    }
 
    public Pessoa getPessoa() {
        return this.pessoa;
    }
 
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}

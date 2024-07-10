package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Pessoa;
import com.generation.blogpessoal.repository.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll(){
		return ResponseEntity.ok(pessoaRepository.findAll());
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable Long id){
        return pessoaRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pessoa>> getByTitle(@PathVariable 
    String nome){
        return ResponseEntity.ok(pessoaRepository
            .findAllByNomeContainingIgnoreCase(nome));
    }
    
    @PostMapping
    public ResponseEntity<Pessoa> post(@Valid @RequestBody Pessoa pessoa){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pessoaRepository.save(pessoa));
    }
    
    @PutMapping
    public ResponseEntity<Pessoa> put(@Valid @RequestBody Pessoa pessoa){
        return pessoaRepository.findById(pessoa.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(pessoaRepository.save(pessoa)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        
        if(pessoa.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        pessoaRepository.deleteById(id);   
	
    }
}

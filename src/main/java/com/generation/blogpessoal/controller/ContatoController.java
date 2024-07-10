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

import com.generation.blogpessoal.model.Contato;
import com.generation.blogpessoal.repository.ContatoRepository;
import com.generation.blogpessoal.repository.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContatoController {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private PessoaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List <Contato>> getAll(){
		return ResponseEntity.ok(contatoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> getById(@PathVariable Long id) {
		return contatoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		} 
	@GetMapping("/contato/{contato}")
	public ResponseEntity<List<Contato>> getByContato(@PathVariable String contato){
		return ResponseEntity.ok(contatoRepository.findAllByContatoContainingIgnoreCase(contato));
	}
	
	@PostMapping
	public ResponseEntity<Contato> post (@Valid @RequestBody Contato contato){
		if (temaRepository.existsById(contato.getId()))
			return ResponseEntity.status(HttpStatus.CREATED)
				.body(contatoRepository.save(contato));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Contato> put (@Valid @RequestBody Contato contato){
		if (contatoRepository.existsById(contato.getId())){
			
		
		if (temaRepository.existsById(contato.getId()))
				return ResponseEntity.status(HttpStatus.OK)
					.body(contatoRepository.save(contato));
					
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe", null);
		}
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		
		if(contato.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		contatoRepository.deleteById(id);
	
	}
	
	
}


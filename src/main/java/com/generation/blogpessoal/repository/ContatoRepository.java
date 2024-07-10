package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

	public List <Contato> findAllByContatoContainingIgnoreCase(@Param("contato") String contato);
}
  
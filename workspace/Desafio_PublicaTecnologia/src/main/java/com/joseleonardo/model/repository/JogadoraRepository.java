package com.joseleonardo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joseleonardo.model.Jogadora;

/* Criação do repository de Jogadora para realizar transições no banco de dados  */
public interface JogadoraRepository extends JpaRepository<Jogadora, Long>{

	/* Pega na requisção o e-mail e ve se existe no banco de dados */
	@Query("SELECT j FROM Jogadora j WHERE j.email = ?1")
	Jogadora findJogadoraByEmail(String email);
	
}

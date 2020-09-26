package com.joseleonardo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joseleonardo.model.Jogadora;

public interface JogadoraRepository extends JpaRepository<Jogadora, Long>{

	@Query("SELECT j FROM Jogadora j WHERE j.email = ?1")
	Jogadora findJogadoraByEmail(String email);
	
}

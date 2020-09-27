package com.joseleonardo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joseleonardo.model.Pontuacao;

public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long>{
	
	@Query("SELECT MIN(p.minimoTemporada) FROM Pontuacao p")
	int findMinTemporada();
	
	@Query("SELECT MAX(p.maximoTemporada) FROM Pontuacao p")
	int findMaxTemporada();
	
	@Query("SELECT MAX(p.quebraRecordeMin) FROM Pontuacao p")
	int findRecordeMin();
	
	@Query("SELECT MAX(p.quebraRecordeMax) FROM Pontuacao p")
	int findRecordeMax();
}

package com.joseleonardo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joseleonardo.model.Pontuacao;

/* Criação do repository de Pontuacao para realizar transições no banco de dados  */
public interface PontuacaoRepository extends JpaRepository<Pontuacao, Long>{
	
	/* Retorna o valor mínimo do mínimo da temporada */
	@Query("SELECT MIN(p.minimoTemporada) FROM Pontuacao p")
	int findMinTemporada();
	
	/* Retorna o valor máximo do máximo da temporada */
	@Query("SELECT MAX(p.maximoTemporada) FROM Pontuacao p")
	int findMaxTemporada();
	
	/* Retorna o valor máximo do recorde mínimo da temporada */
	@Query("SELECT MAX(p.quebraRecordeMin) FROM Pontuacao p")
	int findRecordeMin();
	
	/* Retorna o valor máximo do recorde máximo da temporada */
	@Query("SELECT MAX(p.quebraRecordeMax) FROM Pontuacao p")
	int findRecordeMax();
}

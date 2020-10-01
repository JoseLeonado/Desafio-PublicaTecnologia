package com.joseleonardo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/* Criação da classe e tabela Pontuacao */
@Entity
public class Pontuacao {

	/* Id auto_increment */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Placar é obrigatório")
	@Range(min = 1, max = 1000, message = "Placar entre 1 e 1000")
	private int placar;
	
	private int minimoTemporada;
	
	private int maximoTemporada;
	
	private int quebraRecordeMin;
	
	private int quebraRecordeMax;

	public Pontuacao() {

	}

	public Pontuacao(int placar, int minimoTemporada, int maximoTemporada, int quebraRecordeMin, int quebraRecordeMax) {
		this.placar = placar;
		this.minimoTemporada = minimoTemporada;
		this.maximoTemporada = maximoTemporada;
		this.quebraRecordeMin = quebraRecordeMin;
		this.quebraRecordeMax = quebraRecordeMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlacar() {
		return placar;
	}

	public void setPlacar(int placar) {
		this.placar = placar;
	}

	public int getMinimoTemporada() {
		return minimoTemporada;
	}

	public void setMinimoTemporada(int minimoTemporada) {
		this.minimoTemporada = minimoTemporada;
	}

	public int getMaximoTemporada() {
		return maximoTemporada;
	}

	public void setMaximoTemporada(int maximoTemporada) {
		this.maximoTemporada = maximoTemporada;
	}

	public int getQuebraRecordeMin() {
		return quebraRecordeMin;
	}

	public void setQuebraRecordeMin(int quebraRecordeMin) {
		this.quebraRecordeMin = quebraRecordeMin;
	}

	public int getQuebraRecordeMax() {
		return quebraRecordeMax;
	}

	public void setQuebraRecordeMax(int quebraRecordeMax) {
		this.quebraRecordeMax = quebraRecordeMax;
	}

}

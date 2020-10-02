package com.joseleonardo.repository;


import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joseleonardo.model.Pontuacao;
import com.joseleonardo.model.repository.PontuacaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PontuacaoRepositoryTeste {

	@Autowired
	private PontuacaoRepository pontuacaoRepository;
	
	@Test
	public void testSalvarPontuacao() {
		Pontuacao pontuacao = new Pontuacao(12, 12, 12, 0, 0);
		pontuacaoRepository.save(pontuacao);
	}
	
	@Test
	public void testEditarPontuacao() {
		Pontuacao pontuacao1 = new Pontuacao(12, 12, 12, 0, 0);
		Pontuacao pontuacao2 = new Pontuacao(24, 12, 24, 0, 1);
		pontuacao2 = new Pontuacao(10, 10, 12, 1, 1);
		pontuacaoRepository.saveAll(Arrays.asList(pontuacao1, pontuacao2));
	}
	
	@Test
	public void testExcluirPontuacao() {
		Pontuacao pontuacao1 = new Pontuacao(12, 12, 12, 0, 0);
		Pontuacao pontuacao2 = new Pontuacao(24, 12, 24, 0, 1);
		pontuacao2 = new Pontuacao(10, 10, 12, 1, 1);
		pontuacaoRepository.saveAll(Arrays.asList(pontuacao1, pontuacao2));
		pontuacaoRepository.delete(pontuacao2);
	}
	
}

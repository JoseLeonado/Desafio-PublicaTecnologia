package com.joseleonardo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.joseleonardo.model.Jogadora;
import com.joseleonardo.model.repository.JogadoraRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JogadoraRepositoryTest {

	@Autowired
	private JogadoraRepository jogadoraRepository;
	
	@Test
	public void testSalvarJogadora() {
		Jogadora jogadora = new Jogadora("Pessoa test", "test@junit.com.br", "testando123");
		jogadoraRepository.save(jogadora);
	}
	
}

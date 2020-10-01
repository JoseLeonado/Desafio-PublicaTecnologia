package com.joseleonardo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.joseleonardo.model.Jogadora;
import com.joseleonardo.model.repository.JogadoraRepository;

@SpringBootApplication
public class DesafioPublicaTecnologiaApplication implements WebMvcConfigurer, CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPublicaTecnologiaApplication.class, args);
	}
	
	/* Redirecionamento do /login do WebSecurity para nosso login personalizado */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		
	}
	
	@Autowired
	private JogadoraRepository jogadoraRepository;

	@Override
	public void run(String... args) throws Exception {
		
		/* Gera a senha criptografada */	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
		String senha = encoder.encode("123");

		/* Sempre cria esse objeto ao subir a aplicação e salva no banco de dados */
		Jogadora maria = new Jogadora("Maria", "maria@gmail.com", senha);
		jogadoraRepository.saveAll(Arrays.asList(maria));
		
	}

}

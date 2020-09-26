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
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		
	}
	
	
	@Autowired
	private JogadoraRepository jogadoraRepository;

	@Override
	public void run(String... args) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 

		String resultado = encoder.encode("123");
		System.out.println(resultado);
		
		Jogadora jogadora = new Jogadora("Maria", "maria@gmail.com", resultado);
		jogadoraRepository.saveAll(Arrays.asList(jogadora));
		
	}

}

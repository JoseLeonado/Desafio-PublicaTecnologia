package com.joseleonardo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joseleonardo.model.Jogadora;
import com.joseleonardo.model.repository.JogadoraRepository;

@Service
public class JogadoraService implements UserDetailsService {
	
	@Autowired
	private JogadoraRepository jogadoraRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Jogadora jogadora = jogadoraRepository.findJogadoraByEmail(username);
		
		if (jogadora == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return jogadora;
	}

}

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
		
		/* Passa o e-mail na requisição */
		Jogadora jogadora = jogadoraRepository.findJogadoraByEmail(username);
		
		/* Se o e-mail não for válido, retorna um erro */
		if (jogadora == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		/* Se o e-mail for válido, retorna a jogadora do e-mail */
		return jogadora;
	}

}

package com.joseleonardo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.joseleonardo.model.Pontuacao;
import com.joseleonardo.model.repository.PontuacaoRepository;

@Controller
public class PontuacaoController {
	
	@Autowired
	private PontuacaoRepository pontuacaoRepository;
	
	@GetMapping("/cadastro/pontuacao")
	public String cadastro() {
		return "pagina/cadastro/pontuacao";
	}
	
	@PostMapping("/cadastro/pontuacao/salvar/pontuacao")
	public String salvar(Pontuacao pontuacao) {
		pontuacaoRepository.save(pontuacao);
		return "pagina/cadastro/pontuacao";
	}

}

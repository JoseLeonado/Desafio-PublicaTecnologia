package com.joseleonardo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		return "paginas/cadastro/pontuacao";
	}

	@PostMapping("/cadastro/pontuacao/salvar")
	public String salvar(Pontuacao pontuacao, Model model) {

		List<Pontuacao> pontuacoes = pontuacaoRepository.findAll();

		
		if (pontuacoes.isEmpty()) {
			pontuacao.setPlacar(pontuacao.getPlacar());
			pontuacao.setMinimoTemporada(pontuacao.getPlacar());
			pontuacao.setMaximoTemporada(pontuacao.getPlacar());
			pontuacao.setQuebraRecordeMin(0);
			pontuacao.setQuebraRecordeMax(0);
			pontuacaoRepository.save(pontuacao);
			model.addAttribute("minTemporada", pontuacao.getPlacar());
			model.addAttribute("maxTemporada", pontuacao.getPlacar());
		} else if (!pontuacoes.isEmpty()) {
			int minimoTemporada = pontuacaoRepository.findMinTemporada();
			int maximoTemporada = pontuacaoRepository.findMaxTemporada();
			int recordeMin = pontuacaoRepository.findRecordeMin();
			int recordeMax = pontuacaoRepository.findRecordeMax();
			if (pontuacao.getPlacar() < minimoTemporada) {
				pontuacao.setPlacar(pontuacao.getPlacar());
				pontuacao.setMinimoTemporada(pontuacao.getPlacar());
				pontuacao.setQuebraRecordeMin(recordeMin + 1);
				pontuacao.setMaximoTemporada(maximoTemporada);
				pontuacao.setQuebraRecordeMax(recordeMax);
				pontuacaoRepository.save(pontuacao);
				model.addAttribute("minTemporada", pontuacao.getPlacar());
				model.addAttribute("maxTemporada", maximoTemporada);
			} else if (pontuacao.getPlacar() > maximoTemporada) {
				pontuacao.setPlacar(pontuacao.getPlacar());
				pontuacao.setMaximoTemporada(pontuacao.getPlacar());
				pontuacao.setQuebraRecordeMax(recordeMax + 1);
				pontuacao.setMinimoTemporada(minimoTemporada);
				pontuacao.setQuebraRecordeMin(recordeMin);
				pontuacaoRepository.save(pontuacao);
				model.addAttribute("maxTemporada", pontuacao.getPlacar());
				model.addAttribute("minTemporada", minimoTemporada);
			} else {
				pontuacao.setPlacar(pontuacao.getPlacar());
				pontuacao.setMinimoTemporada(minimoTemporada);
				pontuacao.setMaximoTemporada(maximoTemporada);
				pontuacao.setQuebraRecordeMin(recordeMin);
				pontuacao.setQuebraRecordeMax(recordeMax);
				pontuacaoRepository.save(pontuacao);
				model.addAttribute("minTemporada", minimoTemporada);
				model.addAttribute("maxTemporada", maximoTemporada);
			}
		}
		return "paginas/cadastro/pontuacao";
	}

}

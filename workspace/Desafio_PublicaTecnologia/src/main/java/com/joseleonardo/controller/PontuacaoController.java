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

	@PostMapping("/cadastro/pontuacao/salvar/pontuacao")
	public String salvar(Pontuacao pontuacao, Model model) {

		List<Pontuacao> pontuacoes = pontuacaoRepository.findAll();

		if (pontuacoes.isEmpty()) {
			pontuacao.setPlacar(pontuacao.getPlacar());
			pontuacao.setMinimoTemporada(pontuacao.getPlacar());
			pontuacao.setMaximoTemporada(pontuacao.getPlacar());
			pontuacao.setQuebraRecordeMin(0);
			pontuacao.setQuebraRecordeMax(0);
			pontuacaoRepository.save(pontuacao);
		} else if(!pontuacoes.isEmpty()) {
			for (Pontuacao p : pontuacoes) {
				if(pontuacao.getPlacar() < p.getMinimoTemporada()) {
					int maximoTemporada = pontuacaoRepository.findMaxTemporada();
					pontuacao.setPlacar(pontuacao.getPlacar());
					pontuacao.setMinimoTemporada(pontuacao.getPlacar());
					pontuacao.setQuebraRecordeMin(pontuacao.getQuebraRecordeMin() + 1);
					pontuacao.setMaximoTemporada(maximoTemporada);
					pontuacao.setQuebraRecordeMax(p.getQuebraRecordeMax());
					System.out.println(pontuacaoRepository.findMinTemporada());
					pontuacaoRepository.save(pontuacao);
					break;
				} else if (pontuacao.getPlacar() > p.getMaximoTemporada()) {
					int minimoTemporada = pontuacaoRepository.findMinTemporada();
					pontuacao.setPlacar(pontuacao.getPlacar());
					pontuacao.setMaximoTemporada(pontuacao.getPlacar());
					pontuacao.setQuebraRecordeMax(pontuacao.getQuebraRecordeMax() + 1);
					pontuacao.setMinimoTemporada(minimoTemporada);
					pontuacao.setQuebraRecordeMin(p.getQuebraRecordeMin());
					pontuacaoRepository.save(pontuacao);
					break;
				} else {
					pontuacao.setPlacar(pontuacao.getPlacar());
					pontuacao.setMinimoTemporada(p.getMinimoTemporada());
					pontuacao.setMaximoTemporada(p.getMaximoTemporada());
					pontuacao.setQuebraRecordeMin(p.getQuebraRecordeMin());
					pontuacao.setQuebraRecordeMax(p.getQuebraRecordeMax());
					pontuacaoRepository.save(pontuacao);
					break;
				}
			}
		}
		return "paginas/cadastro/pontuacao";
	}

}

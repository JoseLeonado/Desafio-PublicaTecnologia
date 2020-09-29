package com.joseleonardo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.joseleonardo.model.Pontuacao;
import com.joseleonardo.model.repository.PontuacaoRepository;

@Controller
public class PontuacaoController {

	@Autowired
	private PontuacaoRepository pontuacaoRepository;

	
	/* Retorna para o formulário de pontuação */
	@GetMapping("/cadastro/pontuacao")
	public String cadastro(Pontuacao pontuacao, Model model) {
		return "paginas/cadastro/pontuacao";
	}

	/* Requisição para salvar pontuações e retorna novamente para a tela de formulário */
	@PostMapping("/cadastro/pontuacao/salvar")
	public String salvar(Pontuacao pontuacao, Model model) {

		/* Retorna todas pontuações salva no banco de dados */
		List<Pontuacao> pontuacoes = pontuacaoRepository.findAll();
		
		/* Sempre que a aplicação for iniciada estará sem dados no banco. */
		if (pontuacoes.isEmpty()) { /* O primeiro cadastro sempre cairá aqui */
			pontuacao.setPlacar(pontuacao.getPlacar());/* Pega na requisição o parâmetro do placar */
			pontuacao.setMinimoTemporada(pontuacao.getPlacar());/* O primeiro cadastro sempre terá o mínimo da temporada igual a requisição do placar */
			pontuacao.setMaximoTemporada(pontuacao.getPlacar());/* O primeiro cadastro sempre terá o máximo da temporada igual a requisição do placar */
			pontuacao.setQuebraRecordeMin(0);/* O primeiro cadastro terá o recorde mínimo como  0*/
			pontuacao.setQuebraRecordeMax(0);/* O primeiro cadastro terá o recorde máximo como  0*/
			pontuacaoRepository.save(pontuacao);/* Salva todos os dados no banco de dados */
			model.addAttribute("minTemporada", pontuacao.getPlacar());/* Devolvemos para o front o valor do atual do mínimo da temporada que inicialmente é igual ao placar */
			model.addAttribute("maxTemporada", pontuacao.getPlacar());/* Devolvemos para o front o valor do atual do mínimo da temporada que inicialmente é igual ao placar */
		} else if (!pontuacoes.isEmpty()) {/* A parir do segundo cadastro em diante as requisições cairão aqui pois, já temos pelo menos 1 dado salvo no banco */
			int minimoTemporada = pontuacaoRepository.findMinTemporada();/* Pega no banco valor mínimo da temporada que se inicia em 0 */
			int maximoTemporada = pontuacaoRepository.findMaxTemporada();/* Pega no banco valor máximo da temporada que se inicia em 0 */
			int recordeMin = pontuacaoRepository.findRecordeMin();/* Pega no banco valor mínimo de recorde que se inicia em 0 */
			int recordeMax = pontuacaoRepository.findRecordeMax();/* Pega no banco o valor máximo de recorde que se inicia em 0 */
			if (pontuacao.getPlacar() < minimoTemporada) {/* Caso a requisição seje menor que o valor do mínimo salvo no banco */
				pontuacao.setPlacar(pontuacao.getPlacar());/* Pega na requisição o parâmetro do placar */
				pontuacao.setMinimoTemporada(pontuacao.getPlacar());/* Troca o valor do mínimo para o valor que veio da requisição */
				pontuacao.setQuebraRecordeMin(recordeMin + 1);/* Recorde se inicia com 0 e adicionamos mais 1 pois, o recorde de mínimo foi quebrado */
				pontuacao.setMaximoTemporada(maximoTemporada);/* Permanace com o valor do máximo */
				pontuacao.setQuebraRecordeMax(recordeMax);/* Permanace com o valor do recorde máximo */
				pontuacaoRepository.save(pontuacao);/* Salva os novos dados no banco de dados */
				model.addAttribute("minTemporada", pontuacaoRepository.findMinTemporada());/* Devolvemos para o front o valor do atual do mínimo da temporada */
				model.addAttribute("maxTemporada", pontuacaoRepository.findMaxTemporada());/* Devolvemos para o front o valor do atual do máximo da temporada */
			} else if (pontuacao.getPlacar() > maximoTemporada) {/* Caso a requisição seje maior que o valor do máximo salvo no banco */
				pontuacao.setPlacar(pontuacao.getPlacar());/* Pega na requisição o parâmetro do placar */
				pontuacao.setMaximoTemporada(pontuacao.getPlacar());/* Troca o valor do máximo para o valor que veio da requisição */
				pontuacao.setQuebraRecordeMax(recordeMax + 1);/* Recorde se inicia com 0 e adicionamos mais 1 pois, o recorde de máximo foi quebrado */
				pontuacao.setMinimoTemporada(minimoTemporada);/* Permanace com o valor do mínimo */
				pontuacao.setQuebraRecordeMin(recordeMin);/* Permanace com o valor do recorde mínimo */
				pontuacaoRepository.save(pontuacao);/* Salva os novos dados no banco de dados */
				model.addAttribute("minTemporada", pontuacaoRepository.findMinTemporada());/* Devolvemos para o front o valor do atual do mínimo da temporada */
				model.addAttribute("maxTemporada", pontuacaoRepository.findMaxTemporada());/* Devolvemos para o front o valor do atual do máximo da temporada */
			} else {/* Caso a requisição seje >= ao mínimo ou a requisição seje <= ao máximo  */
				pontuacao.setPlacar(pontuacao.getPlacar());/* Pega na requisição o parâmetro do placar */
				pontuacao.setMinimoTemporada(minimoTemporada);/* Permanace com o valor do mínimo */
				pontuacao.setMaximoTemporada(maximoTemporada);/* Permanace com o valor do máximo */
				pontuacao.setQuebraRecordeMin(recordeMin);/* Permanace com o valor do recorde mínimo */
				pontuacao.setQuebraRecordeMax(recordeMax);/* Permanace com o valor do recorde máximo */
				pontuacaoRepository.save(pontuacao);/* Salva os dados no banco de dados */
				model.addAttribute("minTemporada", pontuacaoRepository.findMinTemporada());/* Devolvemos para o front o valor do atual do mínimo da temporada */
				model.addAttribute("maxTemporada", pontuacaoRepository.findMaxTemporada());/* Devolvemos para o front o valor do atual do máximo da temporada */
			}
		}
		return "paginas/cadastro/pontuacao";
	}
	
	/* Retorna todos os dados salvo no banco; */
	/* redireciona para a página de lista. */
	@GetMapping("/lista/pontuacao")
	public String lista(Model model) {
		model.addAttribute("pontuacoes", pontuacaoRepository.findAll());
		return "paginas/lista/listaPontuacao";
	}
	
	/* Recebe o id como parâmetro; */
	/* Realiza a edição com base no id; */
	/* Retorna para o formulário de editar */
	@GetMapping("/cadastro/pontuacao/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		Pontuacao pontuacao = pontuacaoRepository.getOne (id);
		model.addAttribute("pontuacao", pontuacao);
		return "paginas/editar/editarPontuacao";
	}
	
	/* Recebe o id como parâmetro; */
	/* Realiza a exlclusão com base no id; */
	/* Retorna para a página de lista */
	@GetMapping("/cadastro/pontuacao/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, Model model) {
		pontuacaoRepository.deleteById(id);
		return lista(model);
	}
	
	
	
	
}

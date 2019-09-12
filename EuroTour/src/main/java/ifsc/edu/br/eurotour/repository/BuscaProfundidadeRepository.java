package ifsc.edu.br.eurotour.repository;

import ifsc.edu.br.eurotour.model.grafo.Arco;
import ifsc.edu.br.eurotour.model.grafo.Grafo;
import ifsc.edu.br.eurotour.model.grafo.Vertice;
import ifsc.edu.br.eurotour.model.mapeamento.Caminho;
import ifsc.edu.br.eurotour.services.BuscaProfundidadeService;
import ifsc.edu.br.eurotour.util.BuscaProfundidade;

/**
 * Camada de acesso a dados da {@link BuscaProfundidade}.<br>
 * Provê funções a camada de serviço: {@link BuscaProfundidadeService}
 * 
 * @author Osmar
 *
 */
public interface BuscaProfundidadeRepository {

	/**
	 * Realiza a busca em profundidade dado um {@link Grafo}, a partir de um
	 * {@link Vertice} de origem e de destino
	 * 
	 * @param g       {@link Grafo} que contém os {@link Vertice}s e os
	 *                {@link Arco}s
	 * @param inicial {@link Vertice} que representa a origem da busca
	 * @param destino {@link Vertice} que representa o destino da busca
	 * @return {@link Caminho} que deve ser percorrido
	 */
	public Caminho buscaProfundidade(Grafo g, Vertice inicial, Vertice destino);
}

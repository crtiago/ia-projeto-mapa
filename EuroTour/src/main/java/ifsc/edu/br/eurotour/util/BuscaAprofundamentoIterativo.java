package ifsc.edu.br.eurotour.util;

import java.util.ArrayList;

import ifsc.edu.br.eurotour.model.estruturasdados.Fila;
import ifsc.edu.br.eurotour.model.grafo.Arco;
import ifsc.edu.br.eurotour.model.grafo.Grafo;
import ifsc.edu.br.eurotour.model.grafo.Vertice;

public class BuscaAprofundamentoIterativo {

	// Variável de controle para caso o destino seja encontrado
	boolean encontrou_caminho = false;

	public ArrayList<Vertice> buscaAprofundamentoIterativo(Grafo g, Vertice inicial, Vertice destino) {
		// Lista de Vertices resultantes da busca
		ArrayList<Vertice> vertice_final = new ArrayList<>();

		// Variável para controlar o limite da busca
		int limite = 0;

		// Caso o encontrou_caminho seja true,significa que foi encontrado o vertice
		// de destino,e retornará o vertice_final
		while (!encontrou_caminho) {
			vertice_final = buscaProfundidadeLimitada(g, inicial, destino, limite);

			// Limite é incrementado para caso o caminho não seja encontrado na iteração
			limite++;
		}
		return vertice_final;
	}

	/**
	 * Realiza a busca em profundidade de um certo grafo, a partir de um vertice
	 * inicial
	 * 
	 * @param g       Grafo com as origens, destinos e pesos
	 * @param inicial Vertice inicial (raiz) a começar a busca
	 * @return A lista com os vertices que podem ser alcançados
	 */

	public ArrayList<Vertice> buscaProfundidadeLimitada(Grafo g, Vertice inicial, Vertice destino, int limite) {

		// Lista com todos os vertices do grafo
		ArrayList<Vertice> vertices_grafo = g.obterVertices();

		// Variável contadora para comparar com o limite
		int cont = 0;

		// Lista que vai listar todos os vertices ja visitados
		ArrayList<Vertice> lista_visitados = new ArrayList<>();

		// inicializa todos os vertices do grafo com 0 visitas,
		// 0 distancia, e sem um caminho (vertices anteriores)
		for (int i = 0; i < vertices_grafo.size(); i++) {
			vertices_grafo.get(i).zerarVisitas();
			vertices_grafo.get(i).zerarDistancia();
			vertices_grafo.get(i).setCaminho(null);
		}

		// faz a primeira visita a partir do vertice escolhido como o inicial
		inicial.visitar();
		inicial.definirDistancia(0);
		inicial.setCaminho(null);

		// cria e adiciona o vértice inicial a fila
		Fila fila = new Fila();
		fila.push(inicial);

		// o laço irá se repetir até não ter mais vértices a serem analisados
		while (!fila.estaVazia()) {

			// Recebe o vertice da iteração atual, presente no inicio da fila
			Vertice atual = fila.getInicio().getInfo();

			// retira um vértice da fila
			fila.pop();

			// Verifica se a busca está sendo feita até o limite
			if (cont <= limite) {

				// Verifica se o atual é igual ao destino
				if (atual.equals(destino)) {
					encontrou_caminho = true;
					return lista_visitados;
				} else {

					// Recebe todos os caminhos possíveis(arcos) do vértice da iteração atual
					ArrayList<Arco> lista_filhos = atual.obterArcos();

					// Para cada arco do vértice atual
					for (Arco arco : lista_filhos) {

						// Recebe o vértice de destino do arco (filho)
						Vertice filho = arco.getDestino();

						// entra no if se o vértice filho não foi visitado ainda
						if (filho.obterVisitado() == 0) {

							// marca que o vértice filho foi visitado
							filho.visitar();

							// reajusta a distância do vértice filho
							filho.definirDistancia(atual.obterDistancia() + arco.getPeso());

							// diz que para chegar nesse vértice filho, é a partir de seu anterior (pai)
							filho.setCaminho(atual.toString());

							// insere o vértice filho a fila
							fila.push(filho);

							// adiciona o vértice na lista de visitados
							lista_visitados.add(filho);

							// Verifica se o filho é igual ao destino
							if (filho.equals(destino)) {
								encontrou_caminho = true;
								return lista_visitados;
							}
						}

						// visita o vértice atual (pai)
						atual.visitar();
					}
				}
			}
			// Incrementa o cont para ir para próximo nível da arvore
			cont++;
		}
		return lista_visitados;
	}

}

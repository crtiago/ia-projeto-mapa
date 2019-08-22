package ifsc.edu.br.eurotour.algoritmobusca;

import java.util.ArrayList;

import ifsc.edu.br.eurotour.grafo.Arco;
import ifsc.edu.br.eurotour.grafo.Grafo;
import ifsc.edu.br.eurotour.grafo.Vertice;
import ifsc.edu.br.eurotour.pilha.Pilha;

/**
 * Classe que representa uma busca em profundidade
 * 
 * @author Osmar
 *
 */
public class BuscaProfundidade {

	/**
	 * Realiza a busca em profundidade de um certo grafo, a partir de um vertice
	 * inicial
	 * 
	 * @param g       Grafo com as origens, destinos e pesos
	 * @param inicial Vertice inicial (raiz) a começar a busca
	 * @return A lista com os vertices que podem ser alcançados
	 */
	public ArrayList<Vertice> buscaProfundidade(Grafo g, Vertice inicial) {
		// Lista com todos os vertices do grafo
		ArrayList<Vertice> vertices_grafo = g.obterVertices();

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

		// cria e adiciona o vértice inicial a pilha
		Pilha pilha = new Pilha();
		pilha.push(inicial);

		// o laço irá se repetir até não ter mais vértices a serem analisados
		while (!pilha.estaVazia()) {
			// Recebe o vertice da iteração atual, presente no topo da pilha
			Vertice atual = pilha.getTopo().getInfo();

			// retira um vértice da pilha
			pilha.pop();

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

					// insere o vértice filho a pilha
					pilha.push(filho);

					// adiciona o vértice na lista de visitados
					lista_visitados.add(filho);
				}

				// visita o vértice atual (pai)
				atual.visitar();
			}
		}
		return lista_visitados;
	}
}

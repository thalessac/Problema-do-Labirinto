/*
EP2 - Introdução a Analise de Algoritmo

02/02/2021

Thales Simão do Amaral Camargo - 9017082
Bruno de Oliveira Feitosa - 9017099

*/


class Solution {
	
	public static Map map;
	public static int MAX; //Número máximo do vetor que armazena o caminho

	int [] best_path; //Armazena a solucao otima
	int[] path; //Armazena um caminho candidato
	int path_size; //Armazena o tamanho do caminho
	int path_index; //Indice no vetor path
	int totalItems; //Armazena a quantidade de itens coletados
	int totalValue; //Armazena o valor total dos itens coletados
	int totalWeight; //Armazena o peso total dos itens coletados
	double totalTime; //Armazena o tempo total do percurso
	int[] inventory; //Armazena a posicao dos itens coletados no percurso 
		
	public Solution (Map map){
		this.MAX = 2 * map.getSize();
		this.path = new int[MAX];
		this.inventory = new int[map.nItems()*2];
	}

	public int[] savebestpath(int[] path){
		
		int [] best_path = new int[MAX];
		
		for (int i=0; i<path.length; i++){
			best_path[i] = path[i];
		}
		return best_path;
	}
	
	public void addstep(int lin, int col, int index){
		
		this.path[index]=lin;
		this.path[index + 1]=col;	
	}
	
	public void collectitem(int lin, int col, int item_index){
		this.inventory[item_index]=lin;
		this.inventory[item_index + 1]=col;
	}
}
	
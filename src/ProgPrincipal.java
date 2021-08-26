/*
EP2 - Introdução a Analise de Algoritmo

02/02/2021

Thales Simão do Amaral Camargo - 9017082
Bruno de Oliveira Feitosa - 9017099

*/




import java.io.*;
import java.util.*;

public class ProgPrincipal {

	//public static final boolean DEBUG = false;
			
	static boolean isValid (Map map, int lin, int col){
		/*
		Metodo que verifica se uma posicao no mapa eh valida e pode ser ocupada
		*/
		
		if (lin >= 0 && lin < map.nLines() && col>=0 && col < map.nColumns() && map.free(lin, col)) return true;
		return false;
	
	}
	
	public static void findPath(Map map, Solution candidate, Solution optimal, int criteria, int lin, int col){
		
		/*
		Metodo que recebe um mapa, registros de solucao candidata e otima, criterio e linha e coluna inicial e 
		resolve o problema do labirinto por tentativa e erro usando chamadas recursivas desse proprio metodo.
		*/
				
		if (map.finished(lin, col) && isValid(map,lin,col)){
			//Computar solucao candidata caso chegue na posicao final do labiirinto
			
			candidate.path_size +=1; //Necessario marcar o ultimo passo
			candidate.addstep(lin, col, candidate.path_index);
			candidate.path_index += 2;
			
			//Tratar caso com tesouro na posicao final
			Item item = map.getItem(lin, col); //Verifica a existência de item
			
			if(item != null){

				candidate.totalWeight += item.getWeight();
				candidate.totalItems+=1;
				candidate.totalValue += item.getValue();
			}
			
						
			//Verificar se solucao local eh solucao global
			if (criteria==1 && (optimal.path_size == 0 || candidate.path_size < optimal.path_size) ||
			    criteria==2 && (optimal.path_size == 0 || candidate.path_size > optimal.path_size) ||
				criteria==3 && (optimal.totalValue == 0 || candidate.totalValue > optimal.totalValue) ||
				criteria==4 && (optimal.totalTime == 0.0 || candidate.totalTime < optimal.totalTime) ){
					
					optimal.path_size = candidate.path_size; 									
					optimal.path = candidate.savebestpath(candidate.path);
					optimal.totalItems = candidate.totalItems;
					optimal.totalValue = candidate.totalValue;
					optimal.totalWeight = candidate.totalWeight;
					optimal.totalTime = candidate.totalTime;
					optimal.path_index = candidate.path_index;
							
				}
			
			candidate.path_size -=1; //Desmarcar o ultimo passo
			map.stepback(lin, col);
			candidate.path_index -= 2;
			
			if(item != null){

				candidate.totalWeight -= item.getWeight();
				candidate.totalItems-=1;
				candidate.totalValue -= item.getValue();
			}
				
			return;
		}
	
		
		if (isValid(map, lin, col)){
			
			// efetivação de um passo
			//Cálculo de tempo, valor e peso
			map.step(lin, col);		// marcamos no mapa que a posição está sendo ocupada.
			
			candidate.path_size +=1; //Calcula tamanho do caminho
												
			Item item = map.getItem(lin, col); //Verifica a existência de item
			
			if(item != null){

				candidate.totalWeight += item.getWeight();
				candidate.totalItems+=1;
				candidate.totalValue += item.getValue();
			}
			
			candidate.totalTime += Math.pow(1 + (candidate.totalWeight+0.0)/10, 2);
							
			//O caminho eh registrado da seguinte maneira:
			//[lin_pos0, col_pos0, lin_pos1, col_pos1, ... , lin_posn, colposn]
			
			candidate.addstep(lin, col, candidate.path_index);
			candidate.path_index += 2;
			
			//Mover em todas as direções possíveis conforme se encontra solucao
				
			//Up
			findPath(map, candidate, optimal, criteria, lin - 1, col);
			
			//Down
			findPath(map, candidate, optimal, criteria, lin + 1, col);
			
			//Left
			findPath(map, candidate, optimal, criteria, lin, col - 1);
			
			//Right
			findPath(map, candidate, optimal, criteria, lin, col + 1);
			
			//Se nao ha mais caminho possivel, desmarcar o passo e voltar os registros
			map.stepback(lin, col);

			candidate.path_size -=1;						
			candidate.totalTime -= Math.pow(1 + (candidate.totalWeight+0.0)/10,2);
			
			if(item != null){

				candidate.totalWeight -= item.getWeight();
				candidate.totalItems-=1;
				candidate.totalValue -= item.getValue();
			}
				
			candidate.path_index -=2;			
		}
		
		return;	
	}
		
	
	public static void printSolution(Map map, Solution solution){

		// A partir do mapa e do path contendo a solução, imprime a saída conforme especificações do EP.
		
		int item_index=0;

		System.out.printf("%d %.2f\n", solution.path_size, solution.totalTime);

		for(int i = 0; i < solution.path_index; i += 2){

			int lin = solution.path[i];
			int col = solution.path[i + 1];
			
			System.out.println(lin + " " + col);
			
			Item item = map.getItem(lin, col);
		
			if (item != null){
				solution.collectitem(lin,col,item_index);
				item_index+=2;		
			}
		}
		
		System.out.println(solution.totalItems + " " + solution.totalValue + " " + solution.totalWeight);
		
		for(int i = 0; i < item_index; i += 2){
			
			int lin = solution.inventory[i];
			int col = solution.inventory[i + 1];
			
			System.out.println(lin + " " + col);
		
		}
		
	}

	public static void main(String [] args) throws IOException {
		
		int lin, col;
		
		Map map = new Map(args[0]);		
		Solution candidate = new Solution(map);
		Solution optimal = new Solution(map);

		int criteria = Integer.parseInt(args[1]);
						
		lin = map.getStartLin();
		col = map.getStartCol();
		
		findPath(map, candidate, optimal, criteria, lin, col);
		printSolution(map, optimal);
		//System.out.println("Nenhuma solucao encontrada");
	}
}
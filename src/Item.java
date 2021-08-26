/*
EP2 - Introdução a Analise de Algoritmo

02/02/2021

Thales Simão do Amaral Camargo - 9017082
Bruno de Oliveira Feitosa - 9017099

*/

import java.io.*;
import java.util.*;

class Item {

	private int lin, col, value, weight;

	
	public Item(String s){

		String [] parts = s.split(" +");		
		lin = Integer.parseInt(parts[0]);
		col = Integer.parseInt(parts[1]);
		value = Integer.parseInt(parts[2]);
		weight = Integer.parseInt(parts[3]);
	}

	public Item(int lin, int col, int value, int weight){

		this.lin = lin;
		this.col = col;
		this.value = value;
		this.weight = weight;
	}

	public int getLin(){

		return lin;
	}

	public int getCol(){

		return col;
	}

	public int [] getCoordinates(){

		return new int [] { getLin(), getCol() } ;
	}

	public int getValue(){

		return value;
	}

	public int getWeight(){

		return weight;
	}

	public String toString(){

		return "Item: coordinates = (" + getLin() + ", " + getCol() + "), value = " + getValue() + " weight = " + getWeight();
	}
}
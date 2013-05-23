package model;

import java.util.LinkedList;

import controller.Nodo;

public class GraphMap {
	private LinkedList<Nodo>[] adiacenze;
	private Nodo[] component;
	
	public GraphMap(Mappa m){
		int n = m.getComponent().length;
		this.adiacenze = new LinkedList[n];
		
		for(int i=0;i<n;i++){
			this.adiacenze[i]= new LinkedList<Nodo>();
			for(int j=0;j<6;j++){
				Esagono e = m.getComponent()[i].getAdiacenze()[j];
				if(e!=null){
					Nodo nodo = new Nodo(e);
					this.adiacenze[i].addLast(nodo);
				}
			}
		}
	}
	
	public LinkedList<Nodo>[] getList(){
		return this.adiacenze;
	}
}

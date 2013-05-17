package model;

import java.util.LinkedList;

public class GraphMap {
	private LinkedList<Nodo>[] adiacenze;
	
	public GraphMap(Mappa m){
		int n = m.getComponent().length;
		this.adiacenze = new LinkedList[n];
		int costo=0;
		
		for(int i=0;i<n;i++){
			this.adiacenze[i]= new LinkedList<Nodo>();
			for(int j=0;j<6;j++){
				int id = m.getComponent()[i].getAdiacenze()[j].getId();
				if(m.getComponent()[i].getTerritorio()!=null){
				costo = m.getComponent()[i].getTerritorio().getCosto();
				}
				Nodo nodo = new Nodo(id,costo);
				this.adiacenze[i].addLast(nodo);
			}
		}
		
	}
	
	public LinkedList<Nodo>[] getList(){
		return this.adiacenze;
	}
	
	private class Nodo{
		private int id;
		private int costo;
		
		private Nodo(int id, int costo){
			this.id=id;
			this.costo=costo;
		}
		
		private int getCosto(){
			return this.costo;
		}
		
		private int getId(){
			return this.costo;
		}
	}
}

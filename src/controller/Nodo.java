package controller;

import model.Esagono;

public class Nodo implements Comparable<Nodo>{
	private int id;
	private long costo;
	
	public Nodo(Esagono e){
		this.id=e.getId();
		if(e.getTerritorio()!=null){
			this.costo=e.getTerritorio().getCosto();
		}
		else{
			this.costo = Integer.MAX_VALUE;
		}
	}
	
	public long getCosto(){
		return this.costo;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setCosto(long distance){
		if(distance<0){
			throw new IllegalArgumentException("non possono esistere pesi negativi");
		}
		this.costo=distance;
	}

	public int compareTo(Nodo n) {
		return Long.compare(this.costo,n.getCosto());
	}
}

package controller;

import model.Esagono;

public class Nodo implements Comparable<Nodo>{
	private int id;
	private int costo;
	private long minDistance;
	
	public Nodo(Esagono e){
		this.id=e.getId();
		if(e.getTerritorio()!=null){
			this.costo=e.getTerritorio().getCosto();
		}
		else{
			this.costo = 0;
		}
		this.minDistance = Integer.MAX_VALUE;
	}
	
	public int getCosto(){
		return this.costo;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setDistance(long distance){
		if(distance<0){
			throw new IllegalArgumentException("non possono esistere pesi negativi");
		}
		this.minDistance=distance;
	}
	
	public long getMinDistance(){
		return this.minDistance;
	}

	public int compareTo(Nodo n) {
		return Long.compare(this.minDistance,n.minDistance);
	}
}

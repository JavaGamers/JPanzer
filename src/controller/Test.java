package controller;

import java.util.Iterator;
import java.util.List;

import model.GraphMap;
import model.Mappa;
import model.Montagna;

public class Test {

	public static void main(String[] args) {

		Mappa m = new Mappa(4);
		for(int i=0;i<m.getComponent().length;i++){
			m.getComponent()[i].setTerritorio(new Montagna());
		}
		GraphMap gm = new GraphMap(m);
		Nodo n = new Nodo(m.getComponent()[0]);
		List<Nodo> lista = ShortestPaths.dijkstra(gm,n);
		Iterator<Nodo> it = lista.iterator();
		long[] distanza = new long[m.getComponent().length];
		while(it.hasNext()){
			Nodo u = it.next();
			distanza[u.getId()]= u.getMinDistance();
		//	System.out.println("Nodo: "+u.getId()+" di costo: "+u.getMinDistance());
		}
		
		for(int i=1;i<m.getComponent().length;i++){
			System.out.println("Nodo: "+i+" di costo: "+distanza[i]);
		}
	}
}

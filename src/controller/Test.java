package controller;

import java.util.Iterator;
import java.util.List;

import model.Esagono;
import model.Mappa;
import model.Montagna;

public class Test {

	public static void main(String[] args) {

		Mappa m = new Mappa(4);
		int limit = 15;
		for(int i=0;i<m.getComponent().length;i++){
			m.getComponent()[i].setTerritorio(new Montagna());
		}
		Esagono e = m.getComponent()[0];
		List<Esagono> lista = Dijkstra.shortestPath(m, e, limit);
		Iterator<Esagono> it = lista.iterator();
		long[] distanza = new long[m.getComponent().length];
		while(it.hasNext()){
			Esagono u = it.next();
			distanza[u.getId()]= u.getMinDistance();
			System.out.println("Nodo: "+u.getId()+" di costo: "+u.getMinDistance());
		}
	}
}

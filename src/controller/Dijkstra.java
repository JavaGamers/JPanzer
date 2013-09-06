package controller;

import java.util.LinkedList;
import java.util.PriorityQueue;

import model.Esagono;
import model.Mappa;

public class Dijkstra {

	public static GameMode gameMode = GameMode.getGameMode();

	/*
	 * Metodo che implementa il noto algoritmo con la variante di un numero di
	 * passi limitato
	 */
	public static LinkedList<Esagono> shortestPath(Mappa m, Esagono source,
			int limit) {
		int lenght = m.getComponent().length;
		int[] color = new int[lenght]; // 0-white 1-gray

		for (int i = 0; i < lenght; i++) {
			color[i] = 0;
		}
		//Setto la distanza della sorgente da se stesso a 0
		source.setDistance(0);
		//PriorityQueue di appoggio per l'algoritmo
		PriorityQueue<Esagono> q = new PriorityQueue<Esagono>();
		//Lista degli esagoni raggiungibili da ritornare
		LinkedList<Esagono> shortestPathTree = new LinkedList<Esagono>();
		q.add(source);
		color[source.getId()] = 1;
		boolean finito = false;//finito = true se l'esagono u non è raggiungibile
							   //dall'unità con i passi a disposizione

		/*
		 * Itero finchè la coda di esagoni da esaminare non è vuota
		 * e finchè ho abbastanza passi per proseguire oltre l'esagono estratto
		 */
		while (!q.isEmpty() && !finito) {
			Esagono u = q.poll();
			if (u.getMinDistance() > limit) {
				finito = true;
			} else {
				shortestPathTree.add(u);
				color[u.getId()] = 1;

				/*
				 * Per ogni esagono visito tutte le adiacenze aggiungendole alla coda
				 * nel caso in cui non ne facciano già parte e aggiornandone la minDistance
				 * se necessario
				 */
				for (int i = 0; i < 6; i++) {
					Esagono v = u.getAdiacenze()[i];
					/*Posso agigungere v alla coda di priorità solo se è diverso da null
					 *e se non ha un'unità avversaria posizionata su di esso*/
					if (v != null) {
						if (v.getUnit() == null
								|| v.getUnit().getPlayer() == gameMode
										.getTurno()) {
							int weight = v.getCosto();
							long distanceToU = u.getMinDistance() + weight;
							if (distanceToU < v.getMinDistance()) {
								if (color[v.getId()] == 0) {
									v.setDistance(distanceToU);
									q.add(v);
									color[v.getId()] = 1;
								}
							}
						}
					}
				}
			}
		}
		return shortestPathTree;
	}
}

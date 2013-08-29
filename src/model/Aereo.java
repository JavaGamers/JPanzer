package model;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.imageio.ImageIO;

public class Aereo extends Unità {

	private final static int BASEATT = 80; // attacco di base
	private final static int BASEDIF = 40; // difesa di base
	private final static int PPT = 5; // passi per turno
	public final static String STRNOME = "Aereo";
	public final static int COSTO = 75;

	public Aereo(int n, int player) {
		super(n, player);
		this.att = Aereo.BASEATT;
		this.dif = Aereo.BASEDIF;
		this.passi = Aereo.PPT;
		if (this.player == 1) {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Aereo1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		} else {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Aereo2_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		}
	}

	public void resetPassi() {
		this.passi = Aereo.PPT;
		this.calcolaEsagoniRaggiungibili();
	}

	// l'aereo per muoversi considera unitario il costo di attraversamento di
	// ciascun territorio
	public LinkedList<Esagono> getEsagoniRaggiungibili() {
		if (this.isMoved || this.esagoniRaggiungibili == null) {
			Mappa m = gameMode.getMappa();
			m.resetDistances();
			this.esagoniRaggiungibili = calcolaEsagoniRaggiungibili(m, this.pos, this.passi);
			this.isMoved = false;
		}
		return this.esagoniRaggiungibili;
	}

	private static LinkedList<Esagono> calcolaEsagoniRaggiungibili(Mappa m,
			Esagono source, int passi) {
		int lenght = m.getComponent().length;
		int[] color = new int[lenght]; // 0-white 1-gray

		for (int i = 0; i < lenght; i++) {
			color[i] = 0;
		}
		source.setDistance(0);
		PriorityQueue<Esagono> q = new PriorityQueue<Esagono>();
		LinkedList<Esagono> shortestPathTree = new LinkedList<Esagono>();
		q.add(source);
		color[source.getId()] = 1;
		boolean finito = false;

		while (!q.isEmpty() && !finito) {
			Esagono u = q.poll();
			if (u.getMinDistance() > passi) {
				finito = true;
			} else {
				shortestPathTree.add(u);
				color[u.getId()] = 1;

				for (int i = 0; i < 6; i++) {
					Esagono v = u.getAdiacenze()[i];
					if (v != null) {
						
						// unica differenza rispetto al codice della classe Dijkstra
						int weight = 1;
						long distanceToU = u.getMinDistance() + weight;
						if (distanceToU < v.getMinDistance()) {
							q.remove(v);
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
		return shortestPathTree;

	}

	public String getNome() {
		return STRNOME;
	}

	// attacco e difesa sono relativi ad una singola unità (numUnits = 1)
	public int getAtt() {
		return (int) (BASEATT * (1 + this.esp));
	}

	public int getDef() {
		return (int) (BASEDIF * (1 + this.esp) * this.bonus);
	}

	public void setPassi(int passi) {
		if (passi < 0) {
			throw new IllegalArgumentException(
					"non hanno senso un numero di passi negativo");
		}
		if (passi > Aereo.PPT) {
			throw new IllegalArgumentException(
					"non puoi settare un numero di passi maggiori di quelli disponibili per tale unità");
		}
		this.passi = passi;
		this.isMoved = true;
	}
}
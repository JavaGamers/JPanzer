package model;

// gli esagoni sono comparabili in base al costo del loro territorio
public class Esagono implements Comparable<Esagono> {
	private int id; // numerdo d'ordine all'interno della lista (vedi Mappa)
	private int[] coordinate; // settore - livello - posizione
	private Esagono[] adiacenze; /*
								 * contiene le adiacenze: i lati degli esagoni
								 * sono numerati, a partire da quello in alto,
								 * da 0 a 5 in senso orario
								 */
	private Unità unit; // unità presente sull'esagono
	private Territorio territorio;
	private long minDistance; // distanza minima dall'esagono source (utilizzata
								// in Dijkstra)

	// costruttore tramite coordinate settore - livello - posizione
	public Esagono(int[] c) {
		this.territorio = null;
		this.unit = null;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		this.minDistance = Integer.MAX_VALUE;

		if (c.length != 3)
			throw new IllegalArgumentException(
					"Invalid format, use settore - livello - posizione");

		for (int i = 0; i < coordinate.length; i++) {
			this.coordinate[i] = c[i];
		}

		this.id = getIdFromCoord(c);
	}

	// costruttore tramite numero identificativo id
	public Esagono(int id) {
		this.territorio = null;
		this.unit = null;
		this.id = id;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		this.minDistance = Integer.MAX_VALUE;

		this.coordinate = Esagono.getCoordFromId(id);

	}

	public int getId() {
		return this.id;
	}

	public int[] getCoordinate() {
		return this.coordinate;
	}

	public int getSettore() {
		return this.coordinate[0];
	}

	public int getLivello() {
		return this.coordinate[1];
	}

	public int getPosizione() {
		return this.coordinate[2];
	}

	public Esagono[] getAdiacenze() {
		return this.adiacenze;
	}

	public Unità getUnit() {
		return this.unit;
	}

	public Territorio getTerritorio() {
		return this.territorio;
	}

	public int getCosto() {
		if (this.getTerritorio() != null) {
			return this.getTerritorio().getCosto();
		} else {
			return 0;
		}
	}

	public long getMinDistance() {
		return this.minDistance;
	}

	public void setDistance(long distance) {
		if (distance < 0) {
			throw new IllegalArgumentException(
					"non possono esistere pesi negativi");
		}
		this.minDistance = distance;
	}

	// n è il lato dell'esagono "e" passato come parametro
	public void setAdiacenza(Esagono e, int n) {
		if (e == null)
			throw new IllegalArgumentException("l'esagono pezzente è: "
					+ this.id);
		if (n > 5)
			throw new IllegalArgumentException(
					"Invalid range. Use number from 0 to 5");

		this.adiacenze[n] = e;
		e.adiacenze[Utilities.mod(n + 3, 6)] = this;

	}

	public void setUnit(Unità u) {
		this.unit = u;
		if (u != null) {
			u.setPos(this);
		}
	}

	public void setTerritorio(Territorio t) {
		this.territorio = t;
	}

	/*
	 * algoritmo che dato in ingresso il numero identificativo dell'esagono "id"
	 * dà in uscita le corrispondeti coordinate settore livello posizione
	 */
	public static int[] getCoordFromId(int id) {
		int[] coordinate = new int[3];
		coordinate[1] = 0;
		if (id == 0) {
			coordinate[0] = 0;
			coordinate[2] = 0;
		} else {
			int i = 0;
			while (id >= Utilities.startLiv(i)) {
				i++;
			}
			coordinate[1] = i - 1;

			coordinate[2] = 0;
			coordinate[0] = 0;

			if (coordinate[1] == 1) {
				coordinate[0] = id;

			} else {

				int k = id - Utilities.startLiv(coordinate[1]);
				coordinate[2] = Utilities.mod(k, coordinate[1]);

				if (k == 0) {
					coordinate[0] = 1;
				} else {
					if (coordinate[2] == 0)
						coordinate[0] = k / coordinate[1] + 1;
					else {
						coordinate[0] = Utilities.sup(k / coordinate[1]);
					}
				}

			}
		}
		return coordinate;
	}

	public static int getIdFromCoord(int[] coord) {
		if (coord.length != 3) {
			throw new IllegalArgumentException(
					"usa formato coordinate corretto");
		}
		int id;
		if (coord[1] == 0)
			id = 0;
		if (coord[1] == 1)
			id = coord[0];
		else
			id = (coord[0] - 1) * coord[1] + coord[2]
					+ Utilities.startLiv(coord[1]);

		return id;
	}

	public boolean isAdiacente(Esagono other) {
		boolean trovato = false;
		for (int i = 0; i < 6 && !trovato; i++) {
			if (this.adiacenze[i] != null) {
				if (this.adiacenze[i].equals(other)) {
					trovato = true;
				}
			}
		}
		return trovato;
	}

	/*
	 * questo metodo è strutturato nell'ottica del salvataggio della
	 * mappa/partita
	 */
	public String toString() {

		String s = "";
		s += this.id;
		s += "-";

		if (this.getTerritorio() != null) {
			s += this.getTerritorio().getNome();
			s += "-";
		} else {
			s += " ";
			s += "-";
		}

		if (this.unit != null) {
			s += this.unit.getNome();
			s += "-";
			s += this.unit.getPlayer();
			s += "-";
			s += this.unit.getNumUnits();
			s += "-";
			s += this.unit.getEsp();
			s += "-";
			s += this.unit.getPassi();
			s += "-";
			s += this.unit.hasAlreadyAttack();
		} else {
			s += " ";
			s += "-";
			s += " ";
			s += "-";
			s += " ";
			s += "-";
			s += " ";
			s += "-";
			s += " ";
			s += "-";
			s += " ";
		}

		return s;
	}

	public int compareTo(Esagono o) {
		return Long.compare(this.minDistance, o.minDistance);
	}

	public boolean equals(Esagono o) {
		if (o == null) {
			throw new IllegalArgumentException("confronto con esagono nullo");
		}
		return (this.id == o.getId());
	}
}

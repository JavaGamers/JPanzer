package model;

public class Mappa {
	private int dim; // coincide con il numerdo di livelli della mappa
	private Esagono[] component; // Array contenente tutti gli esagoni della
									// mappa, ordinati in base all'id
	private int selezionato; // indica l'id dell'esagono selezionato. -1 se
								// nessun esagono selezionato

	// Dimensioni standard delle mappe
	public static final int SMALL = 6;
	public static final int MEDIUM = 10;
	public static final int LARGE = 14;
	public static final int EPIC = 18;

	// Passo al costruttore della mappa il numero di livelli che dovr� avere
	public Mappa(int d) {
		this.dim = d;
		// Il metodo Utilities.endLiv(dim) fornisce l'id dell'ultimo esagono del
		// livello dim. Quindi se dim � il numero di livelli della mappa, il
		// numero di esagoni della mappa � Utilities.endLiv(dim)+1 (Considerando
		// anche l'esagono con id 0)
		this.component = new Esagono[Utilities.endLiv(dim) + 1];
		this.selezionato = -1;

		// Costruzione del 1� esagono
		component[0] = new Esagono(0);

		int[] coord = new int[3];

		// costruzione del 1� livello
		for (int i = 1; i <= 6; i++) {
			Esagono e = new Esagono(i);
			this.component[i] = e;
			this.component[0].setAdiacenza(e, i - 1);
			if (i > 1) {
				coord[0] = i - 1;
				coord[1] = 1;
				coord[2] = 0;

				e.setAdiacenza(this.getEsagono(coord), Utilities.mod(i + 3, 6));
				if (i == 6) {
					int[] uno = { 1, 1, 0 };
					e.setAdiacenza(this.getEsagono(uno), 1);
				}
			}
		}

		/* Costruzione degli altri livelli */

		// Scorro i livelli
		for (int l = 2; l <= dim; l++) {
			coord[1] = l;
			// Scorro i settori
			for (int s = 1; s <= 6; s++) {
				coord[0] = s;
				// Scorro le posizioni all'interno del livello
				for (int p = 0; p < l; p++) {
					coord[2] = p;
					Esagono e = new Esagono(coord);
					this.component[e.getId()] = e;

					/* setta adiacenze */
					// Se sto considerano l'esagono in posizione 0 del settore 1
					if (p == 0 && s == 1) {
						int[] pre = { s, l - 1, p };
						e.setAdiacenza(this.getEsagono(pre), 3);
					}
					// Se sto considerando un esagono in posizione 0 di un
					// settore != 1
					if (p == 0 && s > 1) {
						int[] down = { s, l - 1, p };
						int[] left = { s - 1, l, l - 1 };
						e.setAdiacenza(this.getEsagono(down),
								Utilities.mod(s + 2, 6));
						e.setAdiacenza(this.getEsagono(left),
								Utilities.mod(s + 3, 6));
					}
					// Se sto considerando un esagono che ha posizione != 0
					if (p != 0) {
						// Se sto considerando un esagono con posizione massima
						// in un livello
						if (p == l - 1) {
							// Se sto considerando l'ultimo esagono di un
							// settore != 6
							if (s != 6) {
								int[] pre = { s, l, p - 1 };
								int[] dLeft = { s, l - 1, p - 1 };
								int[] dRight = { s + 1, l - 1, 0 };

								e.setAdiacenza(this.getEsagono(pre),
										Utilities.mod(s + 4, 6));
								e.setAdiacenza(this.getEsagono(dLeft),
										Utilities.mod(s + 3, 6));
								e.setAdiacenza(this.getEsagono(dRight),
										Utilities.mod(s + 2, 6));
							}
							// Se sto considerando l'ultimo esagono del settore
							// 6
							else {
								int[] post = { 1, l, 0 };
								int[] dPost = { 1, l - 1, 0 };
								int[] down = { s, l - 1, p - 1 };
								int[] pre = { s, l, p - 1 };
								e.setAdiacenza(this.getEsagono(post), 1);
								e.setAdiacenza(this.getEsagono(dPost), 2);
								e.setAdiacenza(this.getEsagono(down), 3);
								e.setAdiacenza(this.getEsagono(pre), 4);
							}

						}
						// Se sto considerando un esagono che non ha n�
						// posizione 0 n� posizione massima in un settore
						else {

							int[] pre = { s, l, p - 1 };
							int[] dLeft = { s, l - 1, p - 1 };
							int[] dRight = { s, l - 1, p };

							e.setAdiacenza(this.getEsagono(pre),
									Utilities.mod(s + 4, 6));
							e.setAdiacenza(this.getEsagono(dLeft),
									Utilities.mod(s + 3, 6));
							e.setAdiacenza(this.getEsagono(dRight),
									Utilities.mod(s + 2, 6));

						}
					}
				}
			}
		}
	}

	// Metodo che restituisce l'esagono della mappa corrispondente alle
	// coordinate coord = {s,l,p}
	public Esagono getEsagono(int[] coord) {
		if (coord.length != 3)
			throw new IllegalArgumentException(
					"Invalid format, use settore - livello - posizione");
			int id = Esagono.getIdFromCoord(coord);
		return this.component[id];

	}

	public Esagono[] getComponent() {
		return this.component;
	}

	public int getDim() {
		return this.dim;
	}

	public int getSelezionato() {
		return this.selezionato;
	}

	public void setSelezionato(int id) {
		if (id < -1 || id > this.getComponent().length)
			throw new IllegalArgumentException(
					"Invalid given id: insert a valid id or -1 for nothing");
		this.selezionato = id;
	}

	// Metodo che resetta le distanze dei vari esagoni in modo che sia possibile
	// applicare Dijkstra
	public void resetDistances() {
		for (int i = 0; i < this.component.length; i++) {
			Esagono e = this.component[i];
			e.setDistance(Integer.MAX_VALUE);
		}
	}

	public String toString() {
		String s = "" + this.dim + '\n';
		for (int i = 0; i < this.component.length; i++) {
			s += this.component[i].toString() + '\n';
		}
		return s;
	}
}

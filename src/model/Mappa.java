package model;

public class Mappa {
	private int dim; // coincide con il numerdo di livelli della mappa
	private Esagono[] component;
	private int selezionato; // indica l'id dell'esagono selezionato. -1 se
								// nessun esagono selezionato

	public static final int SMALL = 7;
	public static final int MEDIUM = 12;
	public static final int LARGE = 18;
	public static final int EPIC = 22;

	// d= dimensione
	public Mappa(int d) {
		this.dim = d;
		this.component = new Esagono[Esagono.endLiv(dim) + 1];
		this.selezionato = -1;
		component[0] = new Esagono(0);

		int[] coord = new int[3];

		// costruzione del 1° livello
		for (int i = 1; i <= 6; i++) {
			Esagono e = new Esagono(i);
			this.component[i] = e;
			this.component[0].setAdiacenza(e, i - 1);
			if (i > 1) {
				coord[0] = i - 1;
				coord[1] = 1;
				coord[2] = 0;

				e.setAdiacenza(this.getEsagono(coord), Esagono.mod(i + 3, 6));
				if (i == 6) {
					int[] uno = { 1, 1, 0 };
					e.setAdiacenza(this.getEsagono(uno), 1);
				}
			}
		}
		for (int l = 2; l <= dim; l++) {
			coord[1] = l;
			for (int s = 1; s <= 6; s++) {
				coord[0] = s;
				for (int p = 0; p < l; p++) {
					coord[2] = p;
					Esagono e = new Esagono(coord);
					this.component[e.getId()] = e;

					// setta adiacenze
					if (p == 0 && s == 1) {
						int[] pre = { s, l - 1, p };
						e.setAdiacenza(this.getEsagono(pre), 3);
					}
					if (p == 0 && s > 1) {
						int[] down = { s, l - 1, p };
						int[] left = { s - 1, l, l - 1 };
						e.setAdiacenza(this.getEsagono(down),
								Esagono.mod(s + 2, 6));
						e.setAdiacenza(this.getEsagono(left),
								Esagono.mod(s + 3, 6));
					}
					if (p != 0) {
						if (p == l - 1) {
							if (s != 6) {
								int[] pre = { s, l, p - 1 };
								int[] dLeft = { s, l - 1, p - 1 };
								int[] dRight = { s + 1, l - 1, 0 };

								e.setAdiacenza(this.getEsagono(pre),
										Esagono.mod(s + 4, 6));
								e.setAdiacenza(this.getEsagono(dLeft),
										Esagono.mod(s + 3, 6));
								e.setAdiacenza(this.getEsagono(dRight),
										Esagono.mod(s + 2, 6));
							} else {
								int[] post = { 1, l, 0 };
								int[] dPost = { 1, l - 1, 0 };
								int[] down = { s, l - 1, p - 1 };
								int[] pre = { s, l, p - 1 };
								e.setAdiacenza(this.getEsagono(post), 1);
								e.setAdiacenza(this.getEsagono(dPost), 2);
								e.setAdiacenza(this.getEsagono(down), 3);
								e.setAdiacenza(this.getEsagono(pre), 4);
							}

						} else {

							int[] pre = { s, l, p - 1 };
							int[] dLeft = { s, l - 1, p - 1 };
							int[] dRight = { s, l - 1, p };

							e.setAdiacenza(this.getEsagono(pre),
									Esagono.mod(s + 4, 6));
							e.setAdiacenza(this.getEsagono(dLeft),
									Esagono.mod(s + 3, 6));
							e.setAdiacenza(this.getEsagono(dRight),
									Esagono.mod(s + 2, 6));

						}
					}
				}
			}
		}
	}

	public Esagono getEsagono(int[] coord) {
		if (coord.length != 3)
			throw new IllegalArgumentException(
					"Invalid format, use settore - livello - posizione");
		int id;
		if (coord[1] == 0)
			id = 0;
		if (coord[1] == 1)
			id = coord[0];
		else
			id = (coord[0] - 1) * coord[1] + coord[2]
					+ Esagono.startLiv(coord[1]);

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

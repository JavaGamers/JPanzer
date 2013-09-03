package model;

import java.awt.Polygon;
import java.awt.Rectangle;

public class EsagonoGrafico extends Polygon {
	private double xCentro, yCentro; /*
									 * coordinate sullo schermo del centro
									 * dell'esagono con id = 0
									 */
	private double apotema, raggio; /*
									 * raggio= raggio circonferenza circoscritta
									 * all'esagono
									 */

	/*
	 * le ascisse dei vertici dell'esagono sono elencate in senso antiorario a
	 * partire da destra (asse x) le ordinate dei vertici dell'esagono sono in
	 * senso antiorario a partire da destra (asse x) xC, yC coordinate
	 * dell'origine, ovvero del centro dell'esagono root (numero 0)
	 */
	public EsagonoGrafico(int id, double xC, double yC, double r) {
		super();
		super.npoints = 6;
		super.xpoints = new int[6];
		super.ypoints = new int[6];
		this.raggio = r;
		this.apotema = Math.sqrt(3) / 2 * r;
		int s = Esagono.getCoordFromId(id)[0];
		int l = Esagono.getCoordFromId(id)[1];
		int p = Esagono.getCoordFromId(id)[2];

		switch (s) {

		case 0:
			this.xCentro = xC;
			this.yCentro = yC;
			break;

		case 1:
			this.xCentro = 3 / 2 * raggio * p + raggio * p / 2 + xC;
			this.yCentro = -(2 * l - p) * this.apotema + yC;
			break;

		case 2:
			this.xCentro = 3 / 2 * raggio * l + l * raggio / 2 + xC;
			this.yCentro = -(l - 2 * p) * this.apotema + yC;
			break;

		case 3:
			this.xCentro = 3 / 2 * raggio * (l - p) + (l - p) * raggio / 2 + xC;
			this.yCentro = +(l + p) * this.apotema + yC;
			break;

		case 4:
			this.xCentro = -3 / 2 * raggio * p - raggio * p / 2 + xC;
			this.yCentro = +(2 * l - p) * this.apotema + yC;
			break;

		case 5:
			this.xCentro = -3 / 2 * raggio * l - l * raggio / 2 + xC;
			this.yCentro = +(l - 2 * p) * this.apotema + yC;
			break;

		case 6:
			this.xCentro = -3 / 2 * raggio * (l - p) - (l - p) * raggio / 2
					+ xC;
			this.yCentro = -(l + p) * this.apotema + yC;
			break;
		}

		// riempio arrays coordinate
		super.xpoints[0] = (int) (this.xCentro + this.raggio);
		super.xpoints[1] = (int) (this.xCentro + this.raggio / 2);
		super.xpoints[2] = (int) (this.xCentro - this.raggio / 2);
		super.xpoints[3] = (int) (this.xCentro - this.raggio);
		super.xpoints[4] = (int) (this.xCentro - this.raggio / 2);
		super.xpoints[5] = (int) (this.xCentro + this.raggio / 2);

		super.ypoints[0] = (int) this.yCentro;
		super.ypoints[1] = (int) (this.yCentro + this.apotema);
		super.ypoints[2] = (int) (this.yCentro + this.apotema);
		super.ypoints[3] = (int) this.yCentro;
		super.ypoints[4] = (int) (this.yCentro - this.apotema);
		super.ypoints[5] = (int) (this.yCentro - this.apotema);
	}

	public double getApotema() {
		return this.apotema;
	}

	public double getX() {
		return this.xCentro;
	}

	public double getY() {
		return this.yCentro;
	}

	/*
	 * metodo che permette di riutilizzare sempre lo stesso oggetto (evitando
	 * quindi la costruzione di nuovi oggetti) con lo scopo di risparmiare
	 * memoria
	 */
	public void newSet(int id, double xC, double yC, double r) {
		super.npoints = 6;
		super.xpoints = new int[6];
		super.ypoints = new int[6];
		this.raggio = r;
		this.apotema = Math.sqrt(3) / 2 * r;
		// s=settore - l=livello - p=posizione dell'esagono da disegnare
		int s = Esagono.getCoordFromId(id)[0];
		int l = Esagono.getCoordFromId(id)[1];
		int p = Esagono.getCoordFromId(id)[2];

		switch (s) {
		case 0:
			this.xCentro = xC;
			this.yCentro = yC;
			break;

		case 1:
			this.xCentro = 3 / 2 * raggio * p + raggio * p / 2 + xC;
			this.yCentro = -(2 * l - p) * this.apotema + yC;
			break;

		case 2:
			this.xCentro = 3 / 2 * raggio * l + l * raggio / 2 + xC;
			this.yCentro = -(l - 2 * p) * this.apotema + yC;
			break;

		case 3:
			this.xCentro = 3 / 2 * raggio * (l - p) + (l - p) * raggio / 2 + xC;
			this.yCentro = +(l + p) * this.apotema + yC;
			break;

		case 4:
			this.xCentro = -3 / 2 * raggio * p - raggio * p / 2 + xC;
			this.yCentro = +(2 * l - p) * this.apotema + yC;
			break;

		case 5:
			this.xCentro = -3 / 2 * raggio * l - l * raggio / 2 + xC;
			this.yCentro = +(l - 2 * p) * this.apotema + yC;
			break;

		case 6:
			this.xCentro = -3 / 2 * raggio * (l - p) - (l - p) * raggio / 2
					+ xC;
			this.yCentro = -(l + p) * this.apotema + yC;
			break;
		}

		// riempio arrays coordinate
		super.xpoints[0] = (int) (this.xCentro + this.raggio);
		super.xpoints[1] = (int) (this.xCentro + this.raggio / 2);
		super.xpoints[2] = (int) (this.xCentro - this.raggio / 2);
		super.xpoints[3] = (int) (this.xCentro - this.raggio);
		super.xpoints[4] = (int) (this.xCentro - this.raggio / 2);
		super.xpoints[5] = (int) (this.xCentro + this.raggio / 2);

		super.ypoints[0] = (int) this.yCentro;
		super.ypoints[1] = (int) (this.yCentro + this.apotema);
		super.ypoints[2] = (int) (this.yCentro + this.apotema);
		super.ypoints[3] = (int) this.yCentro;
		super.ypoints[4] = (int) (this.yCentro - this.apotema);
		super.ypoints[5] = (int) (this.yCentro - this.apotema);

		this.calculateBounds(this.xpoints, this.ypoints, this.npoints);

	}

	/*
	 * tale metodo calcola i contorni dell'esagono grafico in modo da rimanere
	 * coerenti a seguito dell'invocazione del metodo newSet(int id, double xC,
	 * double yC, double r) ed è ottenuto mediante l'override del medesimo
	 * metodo della classe Polygon dal quale riprende parte del codice
	 */
	private void calculateBounds(int xpoints[], int ypoints[], int npoints) {
		int boundsMinX = Integer.MAX_VALUE;
		int boundsMinY = Integer.MAX_VALUE;
		int boundsMaxX = Integer.MIN_VALUE;
		int boundsMaxY = Integer.MIN_VALUE;
		for (int i = 0; i < npoints; i++) {

			int x = xpoints[i];
			boundsMinX = Math.min(boundsMinX, x);
			boundsMaxX = Math.max(boundsMaxX, x);
			int y = ypoints[i];
			boundsMinY = Math.min(boundsMinY, y);
			boundsMaxY = Math.max(boundsMaxY, y);
		}

		if (this.bounds == null) {
			this.bounds = new Rectangle(boundsMinX, boundsMinY, boundsMaxX
					- boundsMinX, boundsMaxY - boundsMinY);
		} else {
			this.bounds.setBounds(boundsMinX, boundsMinY, boundsMaxX
					- boundsMinX, boundsMaxY - boundsMinY);
		}

	}

}
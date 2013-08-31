package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Artiglieria extends Unità {

	private final static int BASEATT = 100; // attacco di base
	private final static int BASEDIF = 20; // difesa di base
	private final static int PPT = 5; // passi per turno
	public final static String STRNOME = "Artiglieria";
	public final static int COSTO = 55; /*
										 * costo del battaglione minimo (10
										 * unità)
										 */

	public Artiglieria(int n, int player) {
		super(n, player);
		this.att = Artiglieria.BASEATT;
		this.dif = Artiglieria.BASEDIF;
		this.passi = Artiglieria.PPT;
		if (this.player == 1) {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Artiglieria1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		} else {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Artiglieria2_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}

	public String getNome() {

		return STRNOME;
	}

	public void resetPassi() {
		this.passi = Artiglieria.PPT;
		this.calcolaEsagoniRaggiungibili();
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
		if (passi > Artiglieria.PPT) {
			throw new IllegalArgumentException(
					"non puoi settare un numero di passi maggiori di quelli disponibili per tale unità");
		}
		this.passi = passi;
	}
}

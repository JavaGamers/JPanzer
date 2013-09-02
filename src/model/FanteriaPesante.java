package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FanteriaPesante extends Unità {

	private final static int BASEATT = 150; // attacco di base
	private final static int BASEDIF = 100; // difesa di base
	private final static int PPT = 10; // passi per turno
	public final static String STRNOME = "Fanteria Pesante";
	public final static int COSTO = 60; /*
										 * costo del battaglione minimo (10
										 * unità)
										 */

	public FanteriaPesante(int n, int player) {
		super(n, player);
		this.att = FanteriaPesante.BASEATT;
		this.dif = FanteriaPesante.BASEDIF;
		this.passi = FanteriaPesante.PPT;
		if (this.player == 1) {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Fanteria Pesante1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		} else {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Fanteria Pesante2_Icon.png");
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
		this.passi = FanteriaPesante.PPT;
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
		if (passi > FanteriaPesante.PPT) {
			throw new IllegalArgumentException(
					"non puoi settare un numero di passi maggiori di quelli disponibili per tale unità");
		}
		this.passi = passi;
	}
	
	public boolean isSameUnitOf(Unità other) {
		return this.getNome().equals(other.getNome());
	}
}

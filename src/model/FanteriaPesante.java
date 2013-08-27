package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FanteriaPesante extends Unità {

	private final static int BASEATT = 60; // attacco di base
	private final static int BASEDIF = 50; // difesa di base
	private final static int PPT = 8; // passi per turno
	public final static int COSTO = 30;

	public FanteriaPesante(int n, int player) {
		super(n, player);
		this.att = FanteriaPesante.BASEATT;
		this.dif = FanteriaPesante.BASEDIF;
		this.passi = FanteriaPesante.PPT;
		if (this.player == 1) {
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Fanteria Pesante1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		} else {
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Fanteria Pesante2_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		}
	}

	public String getNome() {

		return "fanteriapesante";
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
}

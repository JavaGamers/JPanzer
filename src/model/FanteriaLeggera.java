package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class FanteriaLeggera extends Unità {

	private final static int BASEATT = 40; // attacco di base
	private final static int BASEDIF = 30; // difesa di base
	private final static int PPT = 10; // passi per turno
	public final static int COSTO = 20;

	public FanteriaLeggera(int n, int player) {
		super(n, player);
		this.att = FanteriaLeggera.BASEATT;
		this.dif = FanteriaLeggera.BASEDIF;
		this.passi = FanteriaLeggera.PPT;
		if (this.player == 1) {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Fanteria Leggera1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		} else {
			try {
				URL imgUrl = getClass().getResource(
						"/view/Icon pack/Unit Pack/Fanteria Leggera2_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		}
	}

	public String getNome() {

		return "fanterialeggera";
	}

	public void resetPassi() {
		this.passi = FanteriaLeggera.PPT;
	}

	// attacco e difesa sono relativi ad una singola unità (numUnits = 1)
	public int getAtt() {
		return (int) (BASEATT * (1 + this.esp));
	}

	public int getDef() {
		return (int) (BASEDIF * (1 + this.esp) * this.bonus);
	}
}

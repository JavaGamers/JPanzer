package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FanteriaLeggera extends Unità {

	private final static int BASEATT = 40; // attacco di base
	private final static int BASEDIF = 30; // difesa di base
	private final static double BASERIG = 0; // rigenerazione di base
	private final static int PTT = 10; // passi per turno
	public final static int COSTO = 100;

	public FanteriaLeggera(int n, int player) {
		super(n, player);
		this.att = FanteriaLeggera.BASEATT;
		this.dif = FanteriaLeggera.BASEDIF;
		this.rig = FanteriaLeggera.BASERIG;
		this.passi = FanteriaLeggera.PTT;
		if (this.player == 1) {
			try {
				bImg = ImageIO
						.read(new File(
								"C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Leggera1_Icon.png"));
			} catch (IOException e) {
				// da scrivere
			}
		} else {
			try {
				bImg = ImageIO
						.read(new File(
								"C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Leggera2_Icon.png"));
			} catch (IOException e) {
				// da scrivere
			}
		}
	}

	public String getNome() {

		return "fanterialeggera";
	}

	public void resetPassi() {
		this.passi = FanteriaLeggera.PTT;
	}

	// attacco e difesa sono relativi ad una singola unità (numUnits = 1)
	public int getAtt() {
		return (int) (BASEATT * (1 + this.esp));
	}

	public int getDef() {
		return (int) (BASEDIF *(1 + this.esp)*this.bonus);
	}
}

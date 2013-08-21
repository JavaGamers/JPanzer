package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Panzer extends Unit� {

	private final static int BASEATT = 0; // attacco di base
	private final static int BASEDIF = 0; // difesa di base
	private final static double BASERIG = 0; // rigenerazione di base
	private final static int PTT = 0; // passi per turno
	public final static int COSTO = 100;

	public Panzer(int n, int player) {
		super(n, player);
		this.att = Panzer.BASEATT;
		this.dif = Panzer.BASEDIF;
		this.rig = Panzer.BASERIG;
		this.passi = Panzer.PTT;
		if (this.player == 1) {
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Panzer1_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		} else {
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Panzer2_Icon.png");
				bImg = ImageIO.read(imgUrl);
			} catch (IOException e) {
				// da scrivere
			}
		}
	}

	public String getNome() {

		return "panzer";
	}

	public void resetPassi() {
		this.passi = Panzer.PTT;
	}

	// attacco e difesa sono relativi ad una singola unit� (numUnits = 1)
	public int getAtt() {
		return (int) (BASEATT * (1 + this.esp));
	}

	public int getDef() {
		return (int) (BASEDIF * (1 + this.esp) * this.bonus);
	}
}

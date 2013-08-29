package model;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Panzer extends Unità {

	private final static int BASEATT = 80; // attacco di base
	private final static int BASEDIF = 70; // difesa di base
	private final static int PPT = 6; // passi per turno
	public final static String STRNOME = "Panzer";
	public final static int COSTO = 65;

	public Panzer(int n, int player) {
		super(n, player);
		this.att = Panzer.BASEATT;
		this.dif = Panzer.BASEDIF;
		this.passi = Panzer.PPT;
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

		return STRNOME;
	}

	public void resetPassi() {
		this.passi = Panzer.PPT;
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
		if (passi > Panzer.PPT) {
			throw new IllegalArgumentException(
					"non puoi settare un numero di passi maggiori di quelli disponibili per tale unità");
		}
		this.passi = passi;
	}
}

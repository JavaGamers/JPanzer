package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Collina implements Territorio {

	private final static double BONUS = 1.3; /*
											 * bonus moltiplicativo di
											 * permanenza delle unità sul
											 * territorio (moltiplicato alla
											 * difesa)
											 */
	private BufferedImage bImg; // immagine della collina
	private final static int costo = 2; // costo di attraversamento

	public Collina() {
		try {
			URL imgUrl = getClass().getResource(
					"/view/Icon pack/Land Pack/collina.png");
			bImg = ImageIO.read(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String getNome() {
		return "Collina";
	}

	public int getCosto() {
		return costo;
	}

	public double getBonus() {
		return BONUS;
	}

	public Image getImage() {
		return bImg;
	}

}

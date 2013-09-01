package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Foresta implements Territorio {

	private final static double BONUS = 1.5;/*
											 * bonus moltiplicativo di
											 * permanenza delle unità sul
											 * territorio (moltiplicato alla
											 * difesa)
											 */
	private BufferedImage bImg; // immagine della foresta
	private final static int costo = 3; // Costo di attraversamento

	public Foresta() {
		try {
			URL imgUrl = getClass().getResource(
					"/view/Icon pack/Land Pack/foresta.png");
			bImg = ImageIO.read(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String getNome() {
		return "Foresta";
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
package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Lago implements Territorio {

	private final static double BONUS = 0.8;/*
											 * bonus moltiplicativo di
											 * permanenza delle unità sul
											 * territorio (moltiplicato alla
											 * difesa)
											 */
	private BufferedImage bImg; // immagine della lago
	private final static int costo = Integer.MAX_VALUE; // Costo di
														// attraversamento

	public Lago() {
		try {
			URL imgUrl = getClass().getResource(
					"/view/Icon pack/Land Pack/lago.png");
			bImg = ImageIO.read(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public String getNome() {
		return "Lago";
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
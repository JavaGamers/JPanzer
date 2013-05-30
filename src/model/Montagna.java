package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Montagna implements Territorio {

	private final static double BONUS=1.7; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della montagna
	private final static int costo= 4; // da ridefinire
	
	public Montagna(){
		try {
			URL imgUrl=getClass().getResource("/view/Icon pack/Land Pack/Montagna_Icon.png");
			bImg = ImageIO.read(imgUrl);
	       } catch (IOException e) {
	    	   System.out.println("c'è qualcosa che non va");
	       }
	}

	public String getNome() {
		return "Montagna";
	}

	public int getCosto() {
		return costo;
	}
	
	public double getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}
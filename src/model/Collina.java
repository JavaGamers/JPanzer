package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Collina implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della collina
	private final static int costo= 2; // da ridefinire
	
	public Collina(){
		try {
	           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Land Pack - Copia/Collina_Icon.png"));
	       } catch (IOException e) {
	    	   // da scrivere
	       }
	}
	
	public String getNome() {
		return "Collina";
	}

	public int getCosto() {
		return costo;
	}
	
	public int getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}


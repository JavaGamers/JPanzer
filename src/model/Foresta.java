package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Foresta implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della foresta
	private final static int costo= 4; // da ridefinire
	
	public Foresta(){
		try {
	           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Land Pack - Copia/Forest_Icon.png"));
	       } catch (IOException e) {
	    	   // da scrivere
	       }
	}
	
	public String getNome() {
		return "Foresta";
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
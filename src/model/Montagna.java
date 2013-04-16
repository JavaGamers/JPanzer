package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Montagna implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della montagna
	private final static int costo= 4; // da ridefinire
	
	public Montagna(){
		try {
	           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/montagna.jpeg"));
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
	
	public int getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}
package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Lago implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della lago
	private final static int costo= 0; // non verrà mai attraversato
	
	public Lago(){
		try {
	           bImg = ImageIO.read(new File("da inserire"));
	       } catch (IOException e) {
	    	   // da scrivere
	       }
	}
	
	public String getNome() {
		return "Lago";
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
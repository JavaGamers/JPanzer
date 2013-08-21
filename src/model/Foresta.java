package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Foresta implements Territorio {

	private final static double BONUS=1.6; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della foresta
	private final static int costo= 4; // da ridefinire
	
	public Foresta(){
		try {
			bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Land Pack/foresta.png"));
			
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
	
	public double getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}
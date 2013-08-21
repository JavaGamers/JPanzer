package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Lago implements Territorio {

	private final static double BONUS=0.8; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della lago
	private final static int costo= Integer.MAX_VALUE; // non verrà mai attraversato
	
	public Lago(){
		try {
			bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Land Pack/lago.jpg"));

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
	
	public double getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}
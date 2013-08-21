package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Pianura implements Territorio {
	
	private final static double BONUS=1; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della pianura
	private final static int costo= 1; // da ridefinire
	
	public Pianura(){
		try {
			URL imgUrl=getClass().getResource("/view/Icon pack/Land Pack/pianura.png");
			bImg = ImageIO.read(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
	}

	public String getNome() {
		return "Pianura";
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

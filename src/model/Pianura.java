package model;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pianura extends Component implements Territorio {
	
	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della pianura
	private final static int costo= 1; // da ridefinire
	
	public Pianura(){
		try {
	           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/model/pianura.jpg"));
	       } catch (IOException e) {
	    	   System.out.println("c'è qualcosa che non va");
	       }
	}

	public String getNome() {
		return "Pianura";
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
